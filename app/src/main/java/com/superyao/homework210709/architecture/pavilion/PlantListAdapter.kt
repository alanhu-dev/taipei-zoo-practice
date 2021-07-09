package com.superyao.homework210709.architecture.pavilion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.superyao.homework210709.architecture.pavilion.paging.PlantDiffCallback
import com.superyao.homework210709.databinding.ItemPlantBinding
import com.superyao.homework210709.model.Plant
import com.superyao.homework210709.utils.roundedCornersThumbnail
import timber.log.Timber

class PlantListAdapter(
    private val callback: Callback,
) : ListAdapter<Plant, PlantListAdapter.ViewHolder>(PlantDiffCallback()) {

    interface Callback {
        fun onItemClick(plant: Plant)
    }

    inner class ViewHolder(
        private val binding: ItemPlantBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                callback.onItemClick(getItem(bindingAdapterPosition))
            }
        }

        fun bind(plant: Plant) {
            binding.name.text = plant.fNameCh
            binding.info.text = plant.fAlsoKnown
            binding.image.roundedCornersThumbnail(plant.fPic01URL)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Timber.d("onCreateViewHolder")
        return ViewHolder(
            ItemPlantBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}