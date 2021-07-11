package com.superyao.taipeizoo.architecture.main.plant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DefaultItemAnimator
import com.superyao.taipeizoo.architecture.PlantDetailBottomSheetDialogFragment
import com.superyao.taipeizoo.architecture.main.plant.paging.ItemLoadStateAdapter
import com.superyao.taipeizoo.architecture.main.plant.paging.PlantPagingAdapter
import com.superyao.taipeizoo.databinding.FragmentPlantAllBinding
import com.superyao.taipeizoo.model.Plant
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlantAllFragment : Fragment(), PlantPagingAdapter.Callback {

    private lateinit var binding: FragmentPlantAllBinding

    private lateinit var pagingAdapter: PlantPagingAdapter

    private val viewModel by viewModels<PlantAllViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentPlantAllBinding.inflate(inflater, container, false).apply {
        binding = this
        pagingAdapter = PlantPagingAdapter(this@PlantAllFragment)
        swipeRefresh.setOnRefreshListener { pagingAdapter.refresh() }
        recyclerView.apply {
            adapter = pagingAdapter.run { withLoadStateFooter(ItemLoadStateAdapter(::refresh)) }
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
        }
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.allImagesFlow().collectLatest { data ->
                pagingAdapter.submitData(data)
            }
        }
        lifecycleScope.launch {
            pagingAdapter.loadStateFlow.collectLatest { loadStates ->
                when (loadStates.refresh) {
                    is LoadState.Loading -> {
                        binding.swipeRefresh.isRefreshing = true
                    }
                    is LoadState.NotLoading -> {
                        binding.swipeRefresh.isRefreshing = false
                        binding.empty.visibility = if (pagingAdapter.itemCount == 0) {
                            View.VISIBLE
                        } else {
                            View.INVISIBLE
                        }
                    }
                    is LoadState.Error -> {
                        binding.swipeRefresh.isRefreshing = false
                    }
                }
            }
        }
    }

    override fun onItemClick(plant: Plant?) {
        if (plant != null) {
            val fragment = PlantDetailBottomSheetDialogFragment.newInstance(plant)
            fragment.show(childFragmentManager, fragment.tag)
        }
    }

    companion object {
        fun newInstance() = PlantAllFragment()
    }
}