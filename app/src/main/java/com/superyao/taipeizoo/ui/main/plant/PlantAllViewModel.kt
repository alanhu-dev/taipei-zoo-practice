package com.superyao.taipeizoo.ui.main.plant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.superyao.taipeizoo.data.DataRepository
import com.superyao.taipeizoo.data.model.Plant
import com.superyao.taipeizoo.ui.main.plant.paging.PlantPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PlantAllViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {
    fun plantsFlow(query: String = ""): Flow<PagingData<Plant>> {
        return Pager(PagingConfig(PlantPagingSource.pageSize)) {
            PlantPagingSource(repository, query)
        }.flow.cachedIn(viewModelScope)
    }
}