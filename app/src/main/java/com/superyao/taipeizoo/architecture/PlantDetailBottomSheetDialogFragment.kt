package com.superyao.taipeizoo.architecture

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.superyao.taipeizoo.R
import com.superyao.taipeizoo.databinding.FragmentPlantDetailBinding
import com.superyao.taipeizoo.model.Plant
import com.superyao.taipeizoo.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlantDetailBottomSheetDialogFragment : BottomSheetDialogFragment() {

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
            image.show(plant.fPic01URL, R.drawable.plant)
            name.text = "${plant.fNameCh}\n${plant.fNameEn}\n${plant.fNameLatin}"
            location.text = plant.fLocation
            nickName.text = plant.fAlsoKnown
            brief.text = plant.fBrief
            feature.text = plant.fFeature
            function.text = plant.fFunctionApplication
            update.text = plant.fUpdate
        }
    }.root

    companion object {
        private const val PLANT = "PLANT"

        fun newInstance(plant: Plant) = PlantDetailBottomSheetDialogFragment().apply {
            arguments = Bundle().apply {
                putParcelable(PLANT, plant)
            }
        }
    }
}