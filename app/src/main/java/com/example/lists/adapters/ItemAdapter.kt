package com.example.lists.adapters

import android.os.Handler
import androidx.recyclerview.widget.DiffUtil
import com.example.lists.Items
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class ItemAdapter(
    onItemClicked: (position: Int) -> Unit
) : AsyncListDifferDelegationAdapter<Items>(ItemsDiffUtilCallback()) {

    fun addData(dataViews: ArrayList<Items?>) {
        this.differ.currentList.addAll(dataViews)
        notifyDataSetChanged()
    }

    fun getItemAtPosition(position: Int): Items? {
        return differ.currentList[position]
    }

    fun addLoadingView() {
        //Add loading item
        Handler().post {
            differ.currentList.add(null)
            notifyItemInserted(differ.currentList.size - 1)
        }
    }

    fun removeLoadingView() {
        //Remove loading item
        if (differ.currentList.size != 0) {
            differ.currentList.removeAt(differ.currentList.size - 1)
            notifyItemRemoved(differ.currentList.size)
        }
    }

    init {
        delegatesManager.addDelegate(CompanyAdapterDelegate(onItemClicked))
            .addDelegate(ClientsAdapterDelegate(onItemClicked))
            .addDelegate(CompanySquareAdapterDelegate(onItemClicked))
            .addDelegate(ClientsSquareAdapterDelegate(onItemClicked))
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