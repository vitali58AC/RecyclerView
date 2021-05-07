package com.example.lists

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.lists.databinding.ItemClientBinding
import com.example.lists.databinding.ItemCompanyBinding

class ItemAdapter(
    private val onItemClicked: (position: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Items> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val companyBinding = ItemCompanyBinding.inflate(LayoutInflater.from(parent.context))
        val clientsBinding = ItemClientBinding.inflate(LayoutInflater.from(parent.context))
        return when (viewType) {
            TYPE_COMPANY -> CompanyHolder(companyBinding, onItemClicked)
            TYPE_CLIENT -> ClientsHolder(clientsBinding, onItemClicked)
            else -> error("Incorrect item type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is Items.Company -> TYPE_COMPANY
            is Items.Clients -> TYPE_CLIENT
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CompanyHolder -> {
                val company = items[position].let { it as? Items.Company }
                    ?: error("Company at position $position is not a company")
                holder.bind(company)
            }
            is ClientsHolder -> {
                val client = items[position].let { it as? Items.Clients }
                    ?: error("Client at position $position is not an client")
                holder.bind(client)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateCompany(newCompany: List<Items>) {
        items = newCompany
    }


    abstract class BaseItemHolder(
        view: View,
        onItemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        init {
            view.setOnClickListener {
                onItemClicked(bindingAdapterPosition)
            }

        }

    }

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

    class ClientsHolder(
        private val binding: ItemClientBinding,
        onItemClicked: (position: Int) -> Unit
    ) : BaseItemHolder(binding.root, onItemClicked) {

        @SuppressLint("SetTextI18n")
        fun bind(item: Items.Clients) {
            binding.companyName.text = item.name
            binding.companyDescription.text = item.descriptions
            binding.companyRating.text = "I give at: ${item.rating} stars"
            Glide.with(itemView)
                .load(item.image)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.resource_default)
                .into(binding.logoImage)
        }
    }


    companion object {
        private const val TYPE_COMPANY = 0
        private const val TYPE_CLIENT = 1
    }
}