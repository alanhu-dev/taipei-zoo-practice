package com.superyao.taipeizoo.architecture.main.pavilion

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.superyao.taipeizoo.architecture.pavilion.PavilionActivity
import com.superyao.taipeizoo.databinding.FragmentPavilionBinding
import com.superyao.taipeizoo.model.Pavilion
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PavilionFragment : Fragment(), PavilionListAdapter.Callback {

    private lateinit var binding: FragmentPavilionBinding

    private val viewModel by viewModels<PavilionViewModel>()

    private val pavilionLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        // result
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentPavilionBinding.inflate(inflater, container, false).apply {
        binding = this

        val pavilionAdapter = PavilionListAdapter(this@PavilionFragment)
        recyclerView.apply {
            adapter = pavilionAdapter
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
        }

        swipeRefresh.setOnRefreshListener { loadPavilions() }

        // bind
        viewModel.pavilions.observe(viewLifecycleOwner) {
            pavilionAdapter.submitList(it)
            binding.swipeRefresh.isRefreshing = false
            binding.empty.visibility = if (it.isEmpty()) View.VISIBLE else View.INVISIBLE
        }
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadPavilions()
    }

    private fun loadPavilions() {
        viewModel.loadPavilions()
        binding.swipeRefresh.isRefreshing = true
    }

    override fun onItemClick(pavilion: Pavilion) {
        Intent(activity, PavilionActivity::class.java).apply {
            putExtra(PavilionActivity.PAVILION, pavilion)
        }.let {
            pavilionLauncher.launch(it)
        }
    }

    companion object {
        fun newInstance() = PavilionFragment()
    }
}