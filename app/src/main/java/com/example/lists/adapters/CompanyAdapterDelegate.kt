package com.example.lists.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.lists.Items
import com.example.lists.R
import com.example.lists.databinding.ItemCompanyBinding
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class CompanyAdapterDelegate(
    private val onItemClicked: (position: Int) -> Unit
) :
    AbsListItemAdapterDelegate<Items.Company, Items, CompanyAdapterDelegate.CompanyHolder>() {

    class CompanyHolder(
        private val binding: ItemCompanyBinding,
        onItemClicked: (position: Int) -> Unit
    ) : BaseItemHolder(binding.root, onItemClicked) {

        @SuppressLint("SetTextI18n")
        fun bind(item: Items.Company) {
            binding.companyName.text = item.name
            binding.companyDescription.text = item.descriptions
            binding.companyPrice.text = item.price
            binding.companyLocation.text = item.location
            binding.companyRating.text = "Rating: ${item.rating}"
            itemView.context.resources.getString(R.string.mercury_text)
            Glide.with(itemView)
                .load(item.image)
                .placeholder(R.drawable.resource_default)
                .into(binding.logoImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup): CompanyHolder {
        val companyBinding = ItemCompanyBinding.inflate(LayoutInflater.from(parent.context))
        return CompanyHolder(companyBinding, onItemClicked)
    }

    override fun isForViewType(item: Items, items: MutableList<Items>, position: Int): Boolean {
        return item is Items.Company
    }

    override fun onBindViewHolder(
        item: Items.Company,
        holder: CompanyHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}