package com.example.lists

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lists.Constant.VIEW_TYPE_ITEM
import com.example.lists.Constant.VIEW_TYPE_LOADING
import com.example.lists.adapters.ItemAdapter
import com.example.lists.databinding.FragmentGridBinding
import jp.wasabeef.recyclerview.animators.LandingAnimator


class GridFragment : Fragment() {
    private var _binding: FragmentGridBinding? = null
    private val binding get() = _binding!!
    private var itemAdapter by AutoClearedValue<ItemAdapter>(this)
    private var companyArrayList = arrayListOf(
        Items.CompanySquare(
            id = 1,
            image = "https://img.shgstatic.com/clutchco-static/image/scale/65x65/s3fs-public/logos/3df13e745120719d5ba56f4876b8fa61.png",
            name = "Mercury Develop",
            rating = 5.0
        ), Items.ClientsSquare(
            id = 11,
            image = "https://www.pngjoy.com/pngm/186/3682484_harambe-face-sample-avatar-hd-png-download.png",
            name = "Vitali",
            rating = 5.0
        ), Items.CompanySquare(
            id = 2,
            image = "https://img.shgstatic.com/clutchco-static/image/scale/65x65/s3fs-public/logos/magora_sign_200px-.png",
            name = "Magora",
            rating = 4.7
        ), Items.ClientsSquare(
            id = 12,
            image = "https://pickaface.net/gallery/avatar/20151109_144853_2380_sample.png",
            name = "Oleg",
            rating = 2.0
        ), Items.CompanySquare(
            id = 3,
            image = "https://img.shgstatic.com/clutchco-static/image/scale/65x65/s3fs-public/logos/158064806d43071503ff578e453d660c.png",
            name = "Orangesoft",
            rating = 5.0
        ), Items.ClientsSquare(
            id = 13,
            image = "https://pickaface.net/gallery/avatar/unr_sample_170130_2257_9qgawp.png",
            name = "Barack",
            rating = 2.0
        ), Items.CompanySquare(
            id = 4,
            image = "https://img.shgstatic.com/clutchco-static/image/scale/65x65/s3fs-public/logos/17884187_10154220930360448_3201341747770140275_n.png",
            name = "Andersen",
            rating = 4.9
        ), Items.ClientsSquare(
            id = 14,
            image = "https://pickaface.net/gallery/avatar/InspireBuddy5290e4bdc07a4.png",
            name = "Will",
            rating = 3.0
        ), Items.CompanySquare(
            id = 5,
            image = "https://img.shgstatic.com/clutchco-static/image/scale/65x65/s3fs-public/logos/profile_photo512.png",
            name = "nomtek",
            rating = 4.9
        ),
        Items.CompanySquare(
            id = 6,
            image = "https://img.shgstatic.com/clutchco-static/image/scale/65x65/s3fs-public/logos/5198bbb11bba925df59511218c449f4b.png",
            name = "ChopDawg.com",
            rating = 4.8
        ),
        Items.CompanySquare(
            id = 7,
            image = "https://img.shgstatic.com/clutchco-static/image/scale/65x65/s3fs-public/logos/letterhead_logo.jpg",
            name = "AppUnite",
            rating = 4.9
        )
    )

    private lateinit var loadMoreItemsCells: ArrayList<Items?>
    private lateinit var scrollListener: RecyclerViewLoadMoreScroll
    private lateinit var mLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGridBinding.inflate(inflater, container, false)
        val view = binding.root
        if (savedInstanceState != null) {
            companyArrayList = (savedInstanceState.getParcelableArrayList("LIST_KEY")!!)
        }
        initList()
        //** Set the Layout Manager of the RecyclerView
        //  setRVLayoutManager()
        //** Set the scrollListener of the RecyclerView
        //setRVScrollListener()
        itemAdapter.updateCompany(companyArrayList)
        return view
    }

    private fun initList() {
        itemAdapter = ItemAdapter { position -> deleteItem(position) }
        with(binding.companyList) {
            adapter = itemAdapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.HORIZONTAL
                )
            )
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
            itemAnimator = LandingAnimator()
        }

    }

    private fun loadMoreData() {
        //Add the Loading View
        itemAdapter.addLoadingView()
        //Create the loadMoreItemsCells Arraylist
        loadMoreItemsCells = ArrayList()
        //Get the number of the current Items of the main Arraylist
        val start = itemAdapter.itemCount
        //Load 16 more items
        val end = start + 16
        //Use Handler if the items are loading too fast.
        //If you remove it, the data will load so fast that you can't even see the LoadingView
        Handler(Looper.getMainLooper()).postDelayed({
            for (i in start..end) {
                //Get data and add them to loadMoreItemsCells ArrayList
                loadMoreItemsCells.add(companyArrayList.random())
            }
            //Remove the Loading View
            itemAdapter.removeLoadingView()
            //We adding the data to our main ArrayList
            itemAdapter.addData(loadMoreItemsCells)
            //Change the boolean isLoading to false
            scrollListener.setLoaded()
            //Update the recyclerView in the main thread
            binding.companyList.post {
                itemAdapter.notifyDataSetChanged()
            }
        }, 3000)

    }

    private fun setRVLayoutManager() {
        mLayoutManager = GridLayoutManager(requireContext(), 2)
        itemAdapter = ItemAdapter { position -> deleteItem(position) }
        with(binding.companyList) {
            adapter = itemAdapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.HORIZONTAL
                )
            )
            layoutManager = mLayoutManager
            (mLayoutManager as GridLayoutManager).spanSizeLookup =
                object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when (itemAdapter.getItemViewType(position)) {
                            VIEW_TYPE_ITEM -> 1
                            VIEW_TYPE_LOADING -> 2 //number of columns of the grid
                            else -> -1
                        }
                    }
                }
            setHasFixedSize(true)
            itemAnimator = LandingAnimator()
        }

    }

    private fun setRVScrollListener() {
        scrollListener = RecyclerViewLoadMoreScroll(mLayoutManager as GridLayoutManager)
        scrollListener.setOnLoadMoreListener(object :
            OnLoadMoreListener {
            override fun onLoadMore() {
                loadMoreData()
            }
        })

        binding.companyList.addOnScrollListener(scrollListener)
    }

    private fun deleteItem(position: Int) {
        companyArrayList =
            companyArrayList.filterIndexed { index, _ -> index != position } as ArrayList<Items>
        itemAdapter.updateCompany(companyArrayList)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("LIST_KEY", companyArrayList)
    }
}