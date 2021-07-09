package com.superyao.homework210709.architecture.pavilion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DefaultItemAnimator
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.superyao.dev.toolkit.urlIntent
import com.superyao.homework210709.architecture.MainViewModel
import com.superyao.homework210709.architecture.pavilion.paging.ItemLoadStateAdapter
import com.superyao.homework210709.architecture.pavilion.paging.PlantPagingAdapter
import com.superyao.homework210709.databinding.FragmentPlantBinding
import com.superyao.homework210709.model.Pavilion
import com.superyao.homework210709.model.Plant
import com.superyao.homework210709.utils.roundedCornersThumbnail
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class PlantFragment : BottomSheetDialogFragment(), PlantPagingAdapter.Callback {

    private lateinit var binding: FragmentPlantBinding

    private lateinit var pagingAdapter: PlantPagingAdapter

    private val mainViewModel by activityViewModels<MainViewModel>()
    private val viewModel by viewModels<PlantViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentPlantBinding.inflate(inflater, container, false).apply {
        binding = this
        pagingAdapter = PlantPagingAdapter(this@PlantFragment)
        recyclerView.apply {
            adapter = pagingAdapter.withLoadStateFooter(
                ItemLoadStateAdapter(pagingAdapter::refresh)
            )
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
        }
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Pavilion>(PAVILION)?.let { pavilion ->
            binding.apply {
                name.text = pavilion.eName
                image.roundedCornersThumbnail(pavilion.ePicURL)
                info.text = pavilion.eInfo
                val m = "${pavilion.eCategory}\n${pavilion.eMemo}"
                memo.text = m
                webPage.setOnClickListener {
                    startActivity(urlIntent(pavilion.eURL ?: "https://www.zoo.gov.tw/introduce/"))
                }
            }
            lifecycleScope.launch {
                viewModel.allImagesFlow(pavilion.eName ?: "?").collectLatest { data ->
                    pagingAdapter.submitData(data)
                }
            }
            lifecycleScope.launch {
                pagingAdapter.loadStateFlow.collectLatest { loadStates ->
                    when (loadStates.refresh) {
                        is LoadState.Loading -> {
                        }
                        is LoadState.NotLoading -> {
                        }
                        is LoadState.Error -> {
                        }
                    }
                }
            }
        }
    }

    override fun onItemClick(plant: Plant?) {
    }

    companion object {
        private const val PAVILION = "PAVILION"

        fun newInstance(pavilion: Pavilion) = PlantFragment().apply {
            arguments?.putParcelable(PAVILION, pavilion)
        }
    }
}