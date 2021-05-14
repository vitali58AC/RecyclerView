package com.example.lists.adapters

import android.content.ClipData
import android.os.Handler
import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.example.lists.Constant
import com.example.lists.Items
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class ItemAdapter(
    onItemClicked: (position: Int) -> Unit
) : AsyncListDifferDelegationAdapter<Items>(ItemsDiffUtilCallback()) {

    fun addData(dataViews: ArrayList<Items?>) {
        val temporaryList = differ.currentList + dataViews
        this.differ.submitList(temporaryList)
        //notifyDataSetChanged()
    }

    fun getItemAtPosition(position: Int): Items? {
        return differ.currentList[position]
    }

    fun addLoadingView() {
        //Add loading item
        Handler().post {
            val temporaryList = differ.currentList + null
            differ.submitList(temporaryList)
            //notifyItemInserted(differ.currentList.size - 1)
        }
    }

    fun removeLoadingView() {
        //Remove loading item
        if (differ.currentList.size != 0) {
            val temporaryList =
                differ.currentList - null
            differ.submitList(temporaryList)
            //notifyItemRemoved(differ.currentList.size)
        }
    }

  /*  override fun getItemViewType(position: Int): Int {
        return when (differ.currentList[position]) {
            is Items.Loading -> Constant.VIEW_TYPE_LOADING
            is Items.Company -> Constant.VIEW_TYPE_COMPANY
            is Items.CompanySquare -> Constant.VIEW_TYPE_COMPANY_SQUARE
            is Items.Clients -> Constant.VIEW_TYPE_CLIENTS
            is Items.ClientsSquare -> Constant.VIEW_TYPE_CLIENTS_SQUARE


        }
   *//*     return if (differ.currentList[position] == null) {
            Constant.VIEW_TYPE_LOADING
        } else {
            Constant.VIEW_TYPE_ITEM
        }*//*
    }*/

    init {
        delegatesManager.addDelegate(CompanyAdapterDelegate(onItemClicked))
            .addDelegate(ClientsAdapterDelegate(onItemClicked))
            .addDelegate(CompanySquareAdapterDelegate(onItemClicked))
            .addDelegate(ClientsSquareAdapterDelegate(onItemClicked))
            .addDelegate(LoadingAdapterDelegate(onItemClicked))
    }


    fun updateCompany(newCompany: List<Items>) {
        differ.submitList(newCompany)
    }

    class ItemsDiffUtilCallback : DiffUtil.ItemCallback<Items>() {
        override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
            return when {
                oldItem is Items.Company && newItem is Items.Company -> oldItem.id == newItem.id
                oldItem is Items.Clients && newItem is Items.Clients -> oldItem.id == newItem.id
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
            return oldItem == newItem
        }
    }
}