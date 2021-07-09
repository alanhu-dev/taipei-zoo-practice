package com.superyao.homework210709.architecture.pavilion.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.superyao.homework210709.databinding.ItemPagingFooterBinding

class ItemLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<ItemLoadStateAdapter.LoadStateViewHolder>() {

    inner class LoadStateViewHolder(
        private val binding: ItemPagingFooterBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.refresh.setOnClickListener { retry() }
        }

        fun bind(loadState: LoadState) {
            when (loadState) {
                is LoadState.Loading -> {
                    binding.refresh.visibility = View.INVISIBLE
                    binding.loading.visibility = View.VISIBLE
                }
                is LoadState.NotLoading -> {
                }
                is LoadState.Error -> {
                    binding.refresh.visibility = View.VISIBLE
                    binding.loading.visibility = View.INVISIBLE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder =
        LoadStateViewHolder(
            ItemPagingFooterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) =
        holder.bind(loadState)
}