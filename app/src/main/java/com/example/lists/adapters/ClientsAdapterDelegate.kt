package com.example.lists.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.lists.Items
import com.example.lists.R
import com.example.lists.databinding.ItemClientBinding
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class ClientsAdapterDelegate(
    private val onItemClicked: (position: Int) -> Unit
) :
    AbsListItemAdapterDelegate<Items.Clients, Items, ClientsAdapterDelegate.ClientsHolder>() {

    class ClientsHolder(
        private val binding: ItemClientBinding,
        onItemClicked: (position: Int) -> Unit
    ) : BaseItemHolder(binding.root, onItemClicked) {

        fun bind(item: Items.Clients) {
            binding.companyName.text = item.name
            binding.companyDescription.text = item.descriptions
            binding.companyRating.text =
                itemView.context.resources.getString(R.string.give_rate, item.rating)
            Glide.with(itemView)
                .load(item.image)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.resource_default)
                .into(binding.logoImage)
        }
    }

    override fun isForViewType(item: Items, items: MutableList<Items>, position: Int): Boolean {
        return item is Items.Clients

    }

    override fun onCreateViewHolder(parent: ViewGroup): ClientsHolder {
        val clientsBinding = ItemClientBinding.inflate(LayoutInflater.from(parent.context))
        return ClientsHolder(clientsBinding, onItemClicked)
    }

    override fun onBindViewHolder(
        item: Items.Clients,
        holder: ClientsHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}