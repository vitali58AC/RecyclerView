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

object Constant {
    const val VIEW_TYPE_COMPANY = 1
    const val VIEW_TYPE_COMPANY_SQUARE = 2
    const val VIEW_TYPE_CLIENTS = 3
    const val VIEW_TYPE_CLIENTS_SQUARE = 4
    const val VIEW_TYPE_LOADING = 0
}