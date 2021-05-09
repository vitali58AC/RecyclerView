package com.example.lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.lists.adapters.ItemAdapter
import com.example.lists.databinding.FragmentListBinding
import kotlin.random.Random


class ListFragment : Fragment(), FragmentOnClickListener,
    NeutralOnClickListener {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private var itemAdapter by AutoClearedValue<ItemAdapter>(this)
    private var companyArrayList = arrayListOf<Items>()
    private var typeLayoutManager = 0

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
        typeLayoutManager = requireArguments().getInt(KEY_TYPE)
        initList(typeLayoutManager)
        emptyScreenMessage()
        binding.addReviewFAB.setOnClickListener {
            showAddItemDialog()
        }
        itemAdapter.updateCompany(companyArrayList)
        return view
    }

    private fun initList(typeInt: Int) {
        itemAdapter = ItemAdapter { position -> deleteItem(position) }
        with(binding.companyList) {
            adapter = itemAdapter
            when (typeInt) {
                1 -> {
                    layoutManager = LinearLayoutManager(requireContext())
                    setHasFixedSize(true)
                }
                2 -> {
                    layoutManager = GridLayoutManager(requireContext(), 2)
                    setHasFixedSize(true)
                }
                3 -> {
                    layoutManager =
                        StaggeredGridLayoutManager(
                            2,
                            StaggeredGridLayoutManager.HORIZONTAL
                        )
                    setHasFixedSize(true)
                }
            }
        }
    }

    fun addCompany() {
        val reserveCompany = Items.Company.reserveCompanyList.random()
        reserveCompany.id = Random.nextLong()
        companyArrayList = (arrayListOf(reserveCompany) + companyArrayList) as ArrayList<Items>
        emptyScreenMessage()
        itemAdapter.updateCompany(companyArrayList)
        binding.companyList.scrollToPosition(0)

    }

    private fun deleteItem(position: Int) {
        companyArrayList =
            companyArrayList.filterIndexed { index, _ -> index != position } as ArrayList<Items>
        emptyScreenMessage()
        itemAdapter.updateCompany(companyArrayList)
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
            id = Random.nextLong(),
            image = Items.Clients.avatars.random(),
            name = data as String,
            descriptions = data2 as String,
            rating = data3 as Double
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
        binding.companyList.scrollToPosition(randomPosition)
    }

    override fun onFragmentClick() {
        addCompany()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("LIST_KEY", companyArrayList)
    }

    companion object {
        private const val KEY_TYPE = "type"
        fun newInstance(
            typeInt: Int
        ): ListFragment {
            return ListFragment().withArguments {
                putInt(KEY_TYPE, typeInt)
            }
        }


    }
}