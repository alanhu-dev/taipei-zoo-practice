package com.superyao.taipeizoo.architecture.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.recyclerview.widget.DefaultItemAnimator
import com.superyao.taipeizoo.R
import com.superyao.taipeizoo.architecture.pavilion.PavilionActivity
import com.superyao.taipeizoo.databinding.ActivityMainBinding
import com.superyao.taipeizoo.model.Pavilion
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), PavilionListAdapter.Callback {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel>()

    private val plantAllFragment = PlantAllFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
        refresh()
    }

    private fun initUI() {
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        val pavilionAdapter = PavilionListAdapter(this)
        binding.content.recyclerView.apply {
            adapter = pavilionAdapter
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
        }

        binding.content.swipeRefresh.setOnRefreshListener { refresh() }

        binding.content.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_zoo -> supportFragmentManager.commit { hide(plantAllFragment) }
                R.id.navigation_plant -> supportFragmentManager.commit {
                    if (!plantAllFragment.isAdded) {
                        add(binding.content.plantFragment.id, plantAllFragment)
                    }
                    show(plantAllFragment)
                }
            }
            true
        }

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
        PavilionActivity.launch(this, pavilion)
    }
}