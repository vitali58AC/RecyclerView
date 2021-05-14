package com.example.lists.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.lists.Items
import com.example.lists.databinding.ProgressLoadingBinding
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class LoadingAdapterDelegate(
    private val onItemClicked: (position: Int) -> Unit
) :
    AbsListItemAdapterDelegate<Items.Loading, Items, LoadingAdapterDelegate.LoadingHolder>() {


    class LoadingHolder(
        binding: ProgressLoadingBinding,
        onItemClicked: (position: Int) -> Unit
    ) : BaseItemHolder(binding.root, onItemClicked) {

        fun bind() {
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup): LoadingHolder {
        val loadingBinding = ProgressLoadingBinding.inflate(LayoutInflater.from(parent.context))
        return LoadingHolder(loadingBinding, onItemClicked)
    }

    override fun isForViewType(item: Items, items: MutableList<Items>, position: Int): Boolean {
        return item is Items.Loading
    }

    override fun onBindViewHolder(
        item: Items.Loading,
        holder: LoadingHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind()
    }
}