package com.superyao.homework210709.architecture

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import com.superyao.homework210709.architecture.pavilion.PlantFragment
import com.superyao.homework210709.databinding.ActivityMainBinding
import com.superyao.homework210709.databinding.ItemPavilionBinding
import com.superyao.homework210709.model.Pavilion
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), PavilionListAdapter.Callback {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
        refresh()
    }

    private fun initUI() {
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        // RecyclerView
        val pavilionAdapter = PavilionListAdapter(this)
        binding.content.recyclerView.apply {
            adapter = pavilionAdapter
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
        }

        // swipeRefresh
        binding.content.swipeRefresh.setOnRefreshListener { refresh() }

        // bind
        viewModel.pavilions.observe(this) {
            pavilionAdapter.submitList(it)
            binding.content.swipeRefresh.isRefreshing = false
            binding.content.empty.visibility = if (it.isEmpty()) View.VISIBLE else View.INVISIBLE
        }
    }

    private fun refresh() {
        viewModel.refreshPavilions()
        binding.content.swipeRefresh.isRefreshing = true
    }

    override fun onItemClick(pavilion: Pavilion) {
        PlantFragment.newInstance(pavilion).apply { show(supportFragmentManager, tag) }
    }
}