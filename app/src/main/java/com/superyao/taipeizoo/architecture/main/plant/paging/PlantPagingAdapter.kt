package com.superyao.taipeizoo.architecture.main.plant.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.superyao.taipeizoo.R
import com.superyao.taipeizoo.databinding.ItemPlantBinding
import com.superyao.taipeizoo.model.Plant
import com.superyao.taipeizoo.roundedCornersShow

class PlantPagingAdapter(
    private var callback: Callback,
) : PagingDataAdapter<Plant, PlantPagingAdapter.ViewHolder>(PlantDiffCallback()) {

    interface Callback {
        fun onItemClick(plant: Plant?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemPlantBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemPlantBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                callback.onItemClick(getItem(bindingAdapterPosition))
            }
        }

        fun bind(plant: Plant?) {
            plant?.run {
                binding.name.text = fNameCh
                binding.info.text = fAlsoKnown
                binding.image.roundedCornersShow(fPic01URL, R.drawable.plant)
            }
        }
    }
}

class PlantDiffCallback : DiffUtil.ItemCallback<Plant>() {
    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem == newItem
    }
}