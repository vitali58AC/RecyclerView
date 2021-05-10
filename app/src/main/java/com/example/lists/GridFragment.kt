package com.example.lists

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.ScaleAnimation
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
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
    private var isLoading: Boolean = false


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
        initScrollListener()
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

    private fun initScrollListener() {
        binding.companyList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val gridLayoutManager = binding.companyList.layoutManager as GridLayoutManager?
                    val visiballCount = gridLayoutManager?.findLastCompletelyVisibleItemPosition()
                        ?.plus(1)
                    if (visiballCount == gridLayoutManager?.itemCount) {
                        loadMore()
                    }
                }
                /*
                if (!isLoading) {
                    if (gridLayoutManager != null && gridLayoutManager.findLastCompletelyVisibleItemPosition() == companyArrayList.size - 1) {
                        loadMore()
                        isLoading = true
                    }
                }*/
            }
        })
    }

    private fun loadMore() {
//        companyArrayList += companyArrayList
//        itemAdapter.notifyItemInserted(companyArrayList.size - 1)
        val handler = Handler()
        Handler(Looper.getMainLooper()).postDelayed({
            companyArrayList.removeAt(companyArrayList.size - 1)
            val scrollPosition = companyArrayList.size
            var currentSize = scrollPosition
            val nextLimit = currentSize + 100
            while (currentSize - 1 < nextLimit) {
            companyArrayList.add(companyArrayList.random())
            currentSize++
        }
            isLoading = false
        }, 2000)
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