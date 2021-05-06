package com.example.lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lists.databinding.FragmentListBinding


class ListFragment : Fragment(), FragmentOnClickListener,
    NeutralOnClickListener {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private var itemAdapter by AutoClearedValue<ItemAdapter>(this)
    private var companyArrayList = arrayListOf<Items>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        if (savedInstanceState != null) {
            companyArrayList = (savedInstanceState.getParcelableArrayList("LIST_KEY")!!)
        }
        initList()
        emptyScreenMessage()
        binding.addReviewFAB.setOnClickListener {
            showAddItemDialog()
        }
        itemAdapter.updateCompany(companyArrayList)
        itemAdapter.notifyDataSetChanged()
        return view
    }

    private fun initList() {
        itemAdapter = ItemAdapter { position -> deleteItem(position) }
        with(binding.companyList) {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    fun addCompany() {
        val reserveCompany = Items.Company.reserveCompanyList.random()
        companyArrayList = (arrayListOf(reserveCompany) + companyArrayList) as ArrayList<Items>
        emptyScreenMessage()
        itemAdapter.updateCompany(companyArrayList)
        itemAdapter.notifyItemInserted(0)
        binding.companyList.scrollToPosition(0)

    }

    private fun deleteItem(position: Int) {
        companyArrayList =
            companyArrayList.filterIndexed { index, _ -> index != position } as ArrayList<Items>
        emptyScreenMessage()
        itemAdapter.updateCompany(companyArrayList)
        itemAdapter.notifyItemRemoved(position)
    }

    private fun showAddItemDialog() {
        ClientDialogFragment().show(childFragmentManager, "Dialog")
    }

    private fun emptyScreenMessage() {
        if (companyArrayList.isEmpty()) {
            binding.emptyRecyclerViewMessage.visibility = View.VISIBLE
        } else {
            binding.emptyRecyclerViewMessage.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onFragmentClick(data: Any, data2: Any, data3: Any) {
        val newReview = Items.Clients(
            Items.Clients.avatars.random(),
            data as String,
            data2 as String,
            data3 as Double
        )
        var randomPosition = 0
        if (companyArrayList.size >= 1) {
            randomPosition = (1..companyArrayList.size).random()
        }
        val mutableList = companyArrayList.toMutableList()
        mutableList.add(randomPosition, newReview)
        companyArrayList = mutableList as ArrayList<Items>
        emptyScreenMessage()
        itemAdapter.updateCompany(companyArrayList)
        itemAdapter.notifyItemInserted(randomPosition)
        binding.companyList.scrollToPosition(randomPosition)
    }

    override fun onFragmentClick() {
        addCompany()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("LIST_KEY", companyArrayList)
    }
}