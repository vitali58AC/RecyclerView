package com.example.lists.adapters

import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.DiffUtil
import com.example.lists.Items
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class ItemAdapter(
    onItemClicked: (position: Int) -> Unit
) : AsyncListDifferDelegationAdapter<Items>(ItemsDiffUtilCallback()) {

    fun addData(dataViews: MutableList<Items>) {
        val temporaryList: MutableList<Items> = mutableListOf()
        temporaryList.addAll(differ.currentList)
        temporaryList.addAll(dataViews)
        this.differ.submitList(temporaryList)
    }

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
                oldItem is Items.ClientsSquare && newItem is Items.ClientsSquare -> oldItem.id == newItem.id
                oldItem is Items.CompanySquare && newItem is Items.CompanySquare -> oldItem.id == newItem.id
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
            return oldItem == newItem
        }
    }
}