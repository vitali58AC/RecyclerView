package com.example.lists

interface FragmentOnClickListener {
    fun onFragmentClick(data: Any, data2: Any, data3: Any)
}

interface NeutralOnClickListener {
    fun onFragmentClick()
}

interface ToolbarAddButtonVisible {
    fun onToolbarHide(state: Int)
}

interface OnLoadMoreListener {
    fun onLoadMore()
}
