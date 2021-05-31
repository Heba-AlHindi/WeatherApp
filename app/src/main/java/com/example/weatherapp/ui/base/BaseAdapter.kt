package com.example.weatherapp.ui.base

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.weatherapp.R


abstract class BaseRecyclerAdapter<T>(diffCallback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, BaseViewHolder<ViewBinding>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BaseViewHolder(createBinding(parent, viewType))

    abstract fun createBinding(parent: ViewGroup, viewType: Int): ViewBinding

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding>, position: Int) {
        (holder as BaseViewHolder<*>).binding.root.setTag(R.string.position, position)
        bind(holder.binding, position)
    }

    protected abstract fun bind(binding: ViewBinding, position: Int)
}
