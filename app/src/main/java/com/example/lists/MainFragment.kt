package com.example.lists

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lists.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding by viewBinding(FragmentMainBinding::bind)
    private val toolbarVisibility by lazy {
        activity as? ToolbarAddButtonVisible
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.linearButton.setOnClickListener {
            toolbarVisibility?.onToolbarHide(0)
            val listFragment =
                childFragmentManager.findFragmentById(R.id.container)
            val fragmentTransaction = childFragmentManager.beginTransaction()
            val fragment = ListFragment.newInstance(1)
            if (listFragment == null) {
                fragmentTransaction.add(R.id.container, fragment).commit()
            } else {
                fragmentTransaction.replace(R.id.container, fragment).commit()
            }
        }
        binding.gridButton.setOnClickListener {
            toolbarVisibility?.onToolbarHide(1)
            val listFragment =
                childFragmentManager.findFragmentById(R.id.container)
            val fragmentTransaction = childFragmentManager.beginTransaction()
            val fragment = GridFragment()
            if (listFragment == null) {
                fragmentTransaction.add(R.id.container, fragment).commit()
            } else {
                fragmentTransaction.replace(R.id.container, fragment).commit()
            }
        }
        binding.staggeredGridButton.setOnClickListener {
            toolbarVisibility?.onToolbarHide(1)
            val listFragment =
                childFragmentManager.findFragmentById(R.id.container)
            val fragmentTransaction = childFragmentManager.beginTransaction()
            val fragment = StaggeredFragment()
            if (listFragment == null) {
                fragmentTransaction.add(R.id.container, fragment).commit()
            } else {
                fragmentTransaction.replace(R.id.container, fragment).commit()
            }
        }

    }


    fun addCompanySender() {
        val fragment = childFragmentManager.findFragmentById(R.id.container)
        if (fragment is ListFragment) {
            fragment.addCompany()
        }
    }
}