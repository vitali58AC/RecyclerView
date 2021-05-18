package com.example.lists

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lists.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main), ToolbarAddButtonVisible {
    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listFragment = supportFragmentManager.findFragmentById(R.id.containerActivity)
        if (listFragment == null) {
            val fragment = MainFragment()
            supportFragmentManager.beginTransaction().add(R.id.containerActivity, fragment).commit()
        }
        binding.toolbar.title = "List 2"
        val menuAddButton = binding.toolbar.menu.findItem(R.id.actionMenu)
        menuAddButton.isVisible = false
        menuAddButton.setOnMenuItemClickListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.containerActivity)
            if (fragment is MainFragment) {
                fragment.addCompanySender()
            }

            true
        }


    }

    override fun onToolbarHide(state: Int) {
        val menuAddButton = binding.toolbar.menu.findItem(R.id.actionMenu)
        menuAddButton.isVisible = state != 1
    }
}

