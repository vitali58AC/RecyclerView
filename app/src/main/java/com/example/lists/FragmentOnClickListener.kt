package com.example.lists

interface FragmentOnClickListener {
    fun onFragmentClick(data: Any, data2: Any, data3: Any)
}

interface NeutralOnClickListener {
    fun onFragmentClick()
}

interface ToolbarAddButtonClickListener {
    fun onToolbarClick()
}