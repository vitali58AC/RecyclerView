package com.example.lists

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lists.databinding.ActivityMainBinding
import viewBinding

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
        val listFragment = supportFragmentManager.findFragmentById(R.id.containerActivity)
        if (listFragment == null) {
            val fragment = MainFragment()
            supportFragmentManager.beginTransaction().add(R.id.containerActivity, fragment).commit()
        }
        binding.toolbar.title = "List 2"
        binding.toolbar.menu.findItem(R.id.actionMenu).setOnMenuItemClickListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.containerActivity)
            if (fragment is MainFragment) {
                fragment.addCompanySender()
            }

            true
        }


    }
}