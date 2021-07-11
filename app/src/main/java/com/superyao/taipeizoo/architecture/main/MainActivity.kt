package com.superyao.taipeizoo.architecture.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.superyao.taipeizoo.R
import com.superyao.taipeizoo.architecture.main.pavilion.PavilionFragment
import com.superyao.taipeizoo.architecture.main.plant.PlantAllFragment
import com.superyao.taipeizoo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var bottomNavFragments: Array<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
        setupFragments()
    }

    private fun initUI() {
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        binding.content.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_zoo -> switchPage(PAVILION)
                R.id.navigation_plant -> switchPage(PLANT_ALL)
            }
            true
        }
    }

    private fun setupFragments() {
        bottomNavFragments = arrayOf(
            PavilionFragment.newInstance(),
            PlantAllFragment.newInstance(),
        )
        switchPage(PAVILION)
    }

    private fun switchPage(idx: Int) {
        supportFragmentManager.commit {
            if (!bottomNavFragments[idx].isAdded) {
                add(binding.content.fragmentContainer.id, bottomNavFragments[idx])
            }
            bottomNavFragments.forEach { hide(it) }
            show(bottomNavFragments[idx])
        }
    }

    companion object {
        private const val PAVILION = 0
        private const val PLANT_ALL = 1
    }
}