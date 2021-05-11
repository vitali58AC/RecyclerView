package com.example.lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lists.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.linearButton.setOnClickListener {
            val listFragment = childFragmentManager.findFragmentById(R.id.container)
            val fragmentTransaction = childFragmentManager.beginTransaction()
            val fragment = ListFragment.newInstance(1)
            if (listFragment == null) {
                fragmentTransaction.add(R.id.container, fragment).commit()
            } else {
                fragmentTransaction.replace(R.id.container, fragment).commit()
            }
        }
        binding.gridButton.setOnClickListener {
            val listFragment = childFragmentManager.findFragmentById(R.id.container)
            val fragmentTransaction = childFragmentManager.beginTransaction()
            val fragment = GridFragment()
            if (listFragment == null) {
                fragmentTransaction.add(R.id.container, fragment).commit()
            } else {
                fragmentTransaction.replace(R.id.container, fragment).commit()
            }
        }
        binding.staggeredGridButton.setOnClickListener {
            val listFragment = childFragmentManager.findFragmentById(R.id.container)
            val fragmentTransaction = childFragmentManager.beginTransaction()
            val fragment = StaggeredFragment()
            if (listFragment == null) {
                fragmentTransaction.add(R.id.container, fragment).commit()
            } else {
                fragmentTransaction.replace(R.id.container, fragment).commit()
            }
        }

        return view
    }

    fun addCompanySender() {
        val fragment = childFragmentManager.findFragmentById(R.id.container)
        if (fragment is ListFragment) {
            fragment.addCompany()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}