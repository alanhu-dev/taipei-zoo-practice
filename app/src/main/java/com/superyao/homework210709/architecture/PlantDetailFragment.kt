package com.superyao.homework210709.architecture

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.superyao.homework210709.databinding.FragmentPlantDetailBinding
import com.superyao.homework210709.model.Plant
import com.superyao.homework210709.utils.roundedCornersThumbnail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlantDetailFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentPlantDetailBinding

    private var plant: Plant? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        plant = arguments?.getParcelable(PLANT)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentPlantDetailBinding.inflate(inflater, container, false).apply {
        binding = this
        plant?.let { plant ->
            image.roundedCornersThumbnail(plant.fPic01URL)
            name.text = plant.fNameCh
            info.text = plant.fFeature
            memo.text = plant.fAlsoKnown
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
    }

    companion object {
        private const val PLANT = "PLANT"

        fun newInstance(plant: Plant) = PlantDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(PLANT, plant)
            }
        }
    }
}