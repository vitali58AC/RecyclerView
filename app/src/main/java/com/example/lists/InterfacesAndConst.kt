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

interface OnLoadMoreListener {
    fun onLoadMore()
}

object Constant {
    const val VIEW_TYPE_ITEM = 0
    const val VIEW_TYPE_LOADING = 1
}