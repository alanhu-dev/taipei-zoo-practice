package com.superyao.homework210709.architecture.pavilion

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.superyao.dev.toolkit.urlIntent
import com.superyao.homework210709.databinding.FragmentPlantBinding
import com.superyao.homework210709.model.Pavilion
import com.superyao.homework210709.model.Plant
import com.superyao.homework210709.utils.roundedCornersThumbnail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlantFragment : BottomSheetDialogFragment(), PlantListAdapter.Callback {

    private lateinit var binding: FragmentPlantBinding

    private lateinit var listAdapter: PlantListAdapter

    private val viewModel by viewModels<PlantViewModel>()

    private var pavilion: Pavilion? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        pavilion = arguments?.getParcelable(PAVILION)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentPlantBinding.inflate(inflater, container, false).apply {
        binding = this

        // pavilion
        pavilion?.let { pavilion ->
            image.roundedCornersThumbnail(pavilion.ePicURL)
            name.text = pavilion.eName
            info.text = pavilion.eInfo
            val categoryAndMemo = "${pavilion.eCategory}\n${pavilion.eMemo}"
            memo.text = categoryAndMemo
            webPage.setOnClickListener {
                startActivity(urlIntent(pavilion.eURL ?: "https://www.zoo.gov.tw/introduce/"))
            }
        }

        // plant
        swipeRefresh.setOnRefreshListener { refresh() }
        listAdapter = PlantListAdapter(this@PlantFragment)
        recyclerView.apply {
            adapter = listAdapter
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
        }
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // auto expanded
        dialog?.apply {
            setOnShowListener {
                findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)?.let {
                    BottomSheetBehavior.from(it).apply {
                        state = BottomSheetBehavior.STATE_EXPANDED
                        skipCollapsed = true
                    }
                }
            }
        }

        // bind
        viewModel.plants.observe(viewLifecycleOwner) {
            listAdapter.submitList(it)
            binding.swipeRefresh.isRefreshing = false
        }
        refresh()
    }

    private fun refresh() {
        pavilion?.eName?.let {
            viewModel.refreshPlants(it)
            binding.swipeRefresh.isRefreshing = true
        }
    }

    override fun onItemClick(plant: Plant) {
    }

    companion object {
        private const val PAVILION = "PAVILION"

        fun newInstance(pavilion: Pavilion) = PlantFragment().apply {
            arguments = Bundle().apply {
                putParcelable(PAVILION, pavilion)
            }
        }
    }
}