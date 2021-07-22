package com.superyao.taipeizoo.ui.main.pavilion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.superyao.taipeizoo.R
import com.superyao.taipeizoo.data.model.Pavilion
import com.superyao.taipeizoo.databinding.ItemPavilionBinding
import com.superyao.taipeizoo.roundedCornersShow

class PavilionListAdapter(
    private val callback: Callback,
) : ListAdapter<Pavilion, PavilionListAdapter.ViewHolder>(PavilionDiffCallback()) {

    interface Callback {
        fun onItemClick(pavilion: Pavilion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPavilionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemPavilionBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                callback.onItemClick(getItem(bindingAdapterPosition))
            }
        }

        fun bind(pavilion: Pavilion) {
            binding.name.text = pavilion.eName
            binding.memo.text = if (pavilion.eMemo.isNullOrEmpty()) {
                binding.root.context.getString(R.string.no_closed_information)
            } else {
                pavilion.eMemo
            }
            binding.image.roundedCornersShow(pavilion.ePicURL, R.drawable.zoo)
        }
    }
}

class PavilionDiffCallback : DiffUtil.ItemCallback<Pavilion>() {
    override fun areItemsTheSame(oldItem: Pavilion, newItem: Pavilion): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Pavilion, newItem: Pavilion): Boolean {
        return oldItem == newItem
    }
}