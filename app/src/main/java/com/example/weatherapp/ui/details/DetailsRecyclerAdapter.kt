package com.example.weatherapp.ui.details

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.example.weatherapp.databinding.ItemDetailBinding
import com.example.weatherapp.ui.base.BaseRecyclerAdapter

class DetailsRecyclerAdapter :
    BaseRecyclerAdapter<Pair<String, String>>(detailsCallback) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemDetailBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }

    override fun bind(binding: ViewBinding, position: Int) {
        val detailsBinding = binding as ItemDetailBinding
        detailsBinding.rootView.apply {
            radius = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                10f,
                context.resources.displayMetrics
            )
        }
        // bind data
        detailsBinding.tvTitle.text = currentList[position].first
        detailsBinding.tvValue.text = currentList[position].second
    }
}

val detailsCallback = object : DiffUtil.ItemCallback<Pair<String, String>>() {
    override fun areItemsTheSame(
        oldItem: Pair<String, String>,
        newItem: Pair<String, String>
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: Pair<String, String>,
        newItem: Pair<String, String>
    ): Boolean {
        return oldItem == newItem
    }
}