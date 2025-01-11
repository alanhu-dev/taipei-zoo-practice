package com.superyao.taipeizoo.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.superyao.taipeizoo.R
import com.superyao.taipeizoo.data.model.Plant
import com.superyao.taipeizoo.databinding.FragmentPlantDetailBinding
import com.superyao.taipeizoo.roundedCornersShow
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
            image.roundedCornersShow(plant.safePic01URL, R.drawable.plant)

            var names = plant.fNameCh
            if (plant.fNameEn?.isNotEmpty() == true) {
                names += "\n${plant.fNameEn}"
            }
            if (plant.fNameLatin?.isNotEmpty() == true) {
                names += "\n${plant.fNameLatin}"
            }
            name.text = names

            locationTitle.text = getString(R.string.location)
            location.text = plant.fLocation

            aliasTitle.text = getString(R.string.alias)
            aliasName.text = plant.fAlsoKnown

            briefTitle.text = getString(R.string.brief)
            brief.text = plant.fBrief

            featureTitle.text = getString(R.string.feature)
            feature.text = plant.fFeature

            functionTitle.text = getString(R.string.function)
            function.text = plant.fFunctionApplication

            lastDateTitle.text = getString(R.string.last_update)
            lastDate.text = plant.fUpdate
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