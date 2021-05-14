package com.example.lists.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.lists.Items
import com.example.lists.R
import com.example.lists.databinding.ItemCompanySquareBinding
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class CompanySquareAdapterDelegate(
    private val onItemClicked: (position: Int) -> Unit
) :
    AbsListItemAdapterDelegate<Items.CompanySquare, Items, CompanySquareAdapterDelegate.CompanyHolder>() {


    class CompanyHolder(
        private val binding: ItemCompanySquareBinding,
        onItemClicked: (position: Int) -> Unit
    ) : BaseItemHolder(binding.root, onItemClicked) {

        fun bind(item: Items.CompanySquare) {
            binding.companyName.text = item.name
            binding.companyRating.text =
                itemView.context.resources.getString(R.string.rate_is, item.rating)
            Glide.with(itemView)
                .load(item.image)
                .placeholder(R.drawable.resource_default)
                .into(binding.logoImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup): CompanyHolder {
        val companyBinding = ItemCompanySquareBinding.inflate(LayoutInflater.from(parent.context))
        return CompanyHolder(companyBinding, onItemClicked)
    }

    override fun isForViewType(item: Items, items: MutableList<Items>, position: Int): Boolean {
        return item is Items.CompanySquare
    }

    override fun onBindViewHolder(
        item: Items.CompanySquare,
        holder: CompanyHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}