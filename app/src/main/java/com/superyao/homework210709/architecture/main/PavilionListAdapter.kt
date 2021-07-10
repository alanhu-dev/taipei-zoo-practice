package com.superyao.homework210709.architecture.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.superyao.homework210709.databinding.ItemPavilionBinding
import com.superyao.homework210709.model.Pavilion
import com.superyao.homework210709.utils.roundedCornersThumbnail

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
            binding.info.text = pavilion.eInfo
            binding.memo.text = if (pavilion.eMemo.isNullOrEmpty()) {
                "無休館資訊"
            } else {
                pavilion.eMemo
            }
            binding.image.roundedCornersThumbnail(pavilion.ePicURL)
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