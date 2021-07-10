package com.superyao.homework210709.architecture.pavilion

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import com.superyao.dev.toolkit.color
import com.superyao.dev.toolkit.urlIntent
import com.superyao.homework210709.architecture.PlantDetailFragment
import com.superyao.homework210709.databinding.ActivityPavilionBinding
import com.superyao.homework210709.model.Pavilion
import com.superyao.homework210709.model.Plant
import com.superyao.homework210709.utils.roundedCornersThumbnail
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
        refresh()
    }

    private fun initUI() {
        binding = ActivityPavilionBinding.inflate(layoutInflater).also { setContentView(it.root) }

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { finish() }
        supportActionBar?.title = pavilion?.eName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.collapsingToolbar.setExpandedTitleColor(color(android.R.color.white))
        binding.collapsingToolbar.setCollapsedTitleTextColor(color(android.R.color.white))

        // pavilion

        pavilion?.let { pavilion ->
            binding.image.roundedCornersThumbnail(pavilion.ePicURL)
            binding.info.text = pavilion.eInfo
            val categoryAndMemo = "${pavilion.eCategory}\n${pavilion.eMemo}"
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
            binding.swipeRefresh.isRefreshing = false
        }

        binding.swipeRefresh.setOnRefreshListener { refresh() }

        refresh()
    }

    private fun refresh() {
        pavilion?.eName?.let {
            viewModel.refreshPlants(it)
            binding.swipeRefresh.isRefreshing = true
        }
    }

    override fun onItemClick(plant: Plant) {
        val fragment = PlantDetailFragment.newInstance(plant)
        fragment.show(supportFragmentManager, fragment.tag)
    }

    companion object {
        private const val PAVILION = "PAVILION"

        fun launch(context: Context, pavilion: Pavilion) {
            Intent(context, PavilionActivity::class.java).apply {
                putExtra(PAVILION, pavilion)
            }.let {
                context.startActivity(it)
            }
        }
    }
}