package com.superyao.taipeizoo.ui.pavilion

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import com.superyao.dev.toolkit.urlIntent
import com.superyao.taipeizoo.R
import com.superyao.taipeizoo.data.model.Pavilion
import com.superyao.taipeizoo.data.model.Plant
import com.superyao.taipeizoo.databinding.ActivityPavilionBinding
import com.superyao.taipeizoo.show
import com.superyao.taipeizoo.ui.PlantDetailBottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PavilionActivity : AppCompatActivity(), PlantListAdapter.Callback {

    private lateinit var binding: ActivityPavilionBinding

    private val viewModel by viewModels<PavilionViewModel>()

    private var pavilion: Pavilion? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pavilion = intent.getParcelableExtra(PAVILION)
        initUI()
        loadPlants()
    }

    private fun initUI() {
        binding = ActivityPavilionBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.back.setOnClickListener { finish() }

        // pavilion

        pavilion?.let { pavilion ->
            binding.image.show(pavilion.safePicURL, R.drawable.zoo)
            binding.title.text = pavilion.eName
            binding.info.text = pavilion.eInfo

            val categoryAndMemo = "${pavilion.eCategory}\n" +
                    if (pavilion.eMemo.isNullOrEmpty()) {
                        getString(R.string.no_closed_information)
                    } else {
                        pavilion.eMemo
                    }
            binding.memo.text = categoryAndMemo

            binding.webPage.setOnClickListener {
                startActivity(urlIntent(pavilion.eURL ?: "https://www.zoo.gov.tw/introduce/"))
            }
        }

        // plants

        val plantListAdapter = PlantListAdapter(this)
        binding.recyclerView.apply {
            adapter = plantListAdapter
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
        }

        viewModel.plants.observe(this) {
            plantListAdapter.submitList(it)
            binding.empty.alpha = if (it.isEmpty()) .5f else 0f
            binding.loading.alpha = 0f
        }
    }

    private fun loadPlants() {
        pavilion?.eName?.let { viewModel.loadPlants(it) }
    }

    override fun onItemClick(plant: Plant) {
        val fragment = PlantDetailBottomSheetDialogFragment.newInstance(plant)
        fragment.show(supportFragmentManager, fragment.tag)
    }

    companion object {
        const val PAVILION = "PAVILION"
    }
}