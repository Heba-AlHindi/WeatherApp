package com.example.weatherapp.ui.details

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.example.weatherapp.databinding.ItemDetailBinding
import com.example.weatherapp.ui.base.BaseRecyclerAdapter

class DetailsRecyclerAdapter :
    BaseRecyclerAdapter<Pair<String, String>>(diffCallback) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemDetailBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }

    override fun bind(binding: ViewBinding, position: Int) {
        val detailBinding = binding as ItemDetailBinding
        detailBinding.rootView.apply {
            radius = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                10f,
                context.resources.displayMetrics
            )
        }
        // bind data
        detailBinding.tvTitle.text = currentList[position].first
        detailBinding.tvValue.text = currentList[position].second
    }
}

val diffCallback = object : DiffUtil.ItemCallback<Pair<String, String>>() {
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