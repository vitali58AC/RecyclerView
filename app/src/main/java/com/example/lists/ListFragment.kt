package com.example.lists

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lists.adapters.ItemAdapter
import com.example.lists.databinding.FragmentListBinding
import jp.wasabeef.recyclerview.animators.OvershootInLeftAnimator
import kotlin.random.Random


class ListFragment : Fragment(), FragmentOnClickListener,
    NeutralOnClickListener {
    private val binding: FragmentListBinding by viewBinding(CreateMethod.INFLATE)
    private var itemAdapter by AutoClearedValue<ItemAdapter>(this)
    private var companyArrayList = arrayListOf<Items>()
    private var typeLayoutManager = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = binding.root
        if (savedInstanceState != null) {
            companyArrayList = (savedInstanceState.getParcelableArrayList("LIST_KEY")!!)
        }
        typeLayoutManager = requireArguments().getInt(KEY_TYPE)
        initList()
        emptyScreenMessage()
        binding.addReviewFAB.setOnClickListener {
            showAddItemDialog()
        }
        itemAdapter.updateCompany(companyArrayList)
        return view
    }

    private fun initList() {
        itemAdapter = ItemAdapter { position -> deleteItem(position) }
        with(binding.companyList) {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            itemAnimator = OvershootInLeftAnimator()
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