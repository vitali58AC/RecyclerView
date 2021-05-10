package com.example.lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.lists.adapters.ItemAdapter
import com.example.lists.databinding.FragmentStaggeredBinding
import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator


class StaggeredFragment : Fragment() {
    private var _binding: FragmentStaggeredBinding? = null
    private val binding get() = _binding!!
    private var itemAdapter by AutoClearedValue<ItemAdapter>(this)
    private var companyArrayList = arrayListOf(
        Items.CompanySquare(
            id = 1,
            image = "https://img.shgstatic.com/clutchco-static/image/scale/65x65/s3fs-public/logos/3df13e745120719d5ba56f4876b8fa61.png",
            name = "Mercury Develop",
            rating = 5.0
        ), Items.Company(
            id = 7,
            image = "https://img.shgstatic.com/clutchco-static/image/scale/65x65/s3fs-public/logos/letterhead_logo.jpg",
            name = "AppUnite",
            price = "\$25 - \$49 / hr",
            descriptions = "AppUnite specializes in creating highly-usable applications for Android, iOS and web apps in Elixir and Phoenix, React, Vue.js.",
            location = "▽ Poznan, Poland",
            rating = 4.9
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
        ), Items.Company(
            id = 6,
            image = "https://img.shgstatic.com/clutchco-static/image/scale/65x65/s3fs-public/logos/5198bbb11bba925df59511218c449f4b.png",
            name = "ChopDawg.com",
            price = "$150 - $199 / hr",
            descriptions = "Since 2009, we've partnered with startups and enterprises around the world to launch 350+ next-generation apps.",
            location = "▽ Philadelphia, PA",
            rating = 4.8
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
        ), Items.Company(
            id = 5,
            image = "https://img.shgstatic.com/clutchco-static/image/scale/65x65/s3fs-public/logos/profile_photo512.png",
            name = "nomtek",
            price = "\$50 - \$99 / hr",
            descriptions = "As a company, we focus mostly on mobile app design and development and also on Augmented and Mixed Reality products.",
            location = "▽ Wroclaw, Poland",
            rating = 4.9
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStaggeredBinding.inflate(inflater, container, false)
        val view = binding.root
        if (savedInstanceState != null) {
            companyArrayList = (savedInstanceState.getParcelableArrayList("LIST_KEY")!!)
        }
        initList()
        itemAdapter.updateCompany(companyArrayList)
        return view
    }

    private fun initList() {
        itemAdapter = ItemAdapter { position -> deleteItem(position) }
        with(binding.companyList) {
            adapter = itemAdapter
            layoutManager =
                StaggeredGridLayoutManager(
                    3,
                    StaggeredGridLayoutManager.HORIZONTAL
                )
            addItemDecoration(ItemOffsetDecoration(requireContext()))
            itemAnimator = FlipInTopXAnimator()
        }
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