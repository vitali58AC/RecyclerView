package com.example.lists

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ItemAdapter(
    private val onItemClicked: (position: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Items> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_COMPANY -> CompanyHolder(parent.inflate(R.layout.item_company), onItemClicked)
            TYPE_CLIENT -> ClientsHolder(parent.inflate(R.layout.item_client), onItemClicked)
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
        private val nameTextView = view.findViewById<TextView>(R.id.companyName)
        private val descriptionTextView = view.findViewById<TextView>(R.id.companyDescription)

        init {
            view.setOnClickListener {
                onItemClicked(bindingAdapterPosition)
            }

        }

        protected fun bindBaseInfo(
            name: String,
            text: String,
        ) {
            nameTextView.text = name
            descriptionTextView.text = text
        }

    }

    class CompanyHolder(
        view: View,
        onItemClicked: (position: Int) -> Unit
    ) : BaseItemHolder(view, onItemClicked) {
        private val priceTextView = view.findViewById<TextView>(R.id.companyPrice)
        private val locationTextView = view.findViewById<TextView>(R.id.companyLocation)
        private val imageView = view.findViewById<ImageView>(R.id.logoImage)
        private val ratingTextView = view.findViewById<TextView>(R.id.companyRating)

        @SuppressLint("SetTextI18n")
        fun bind(item: Items.Company) {
            bindBaseInfo(item.name, item.text)
            priceTextView.text = item.price
            locationTextView.text = item.location
            ratingTextView.text = "Rating: ${item.rating}"
            itemView.context.resources.getString(R.string.mercury_text)
            Glide.with(itemView)
                .load(item.image)
                .placeholder(R.drawable.resource_default)
                .into(imageView)
        }

    }

    class ClientsHolder(
        view: View,
        onItemClicked: (position: Int) -> Unit
    ) : BaseItemHolder(view, onItemClicked) {
        private val imageView = view.findViewById<ImageView>(R.id.logoImage)
        private val ratingTextView = view.findViewById<TextView>(R.id.companyRating)

        @SuppressLint("SetTextI18n")
        fun bind(item: Items.Clients) {
            bindBaseInfo(item.name, item.text)
            ratingTextView.text = "I give at: ${item.rating} stars"
            Glide.with(itemView)
                .load(item.image)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.resource_default)
                .into(imageView)
        }
    }


    companion object {
        private const val TYPE_COMPANY = 0
        private const val TYPE_CLIENT = 1
    }
}