package com.superyao.taipeizoo.architecture.main.plant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.superyao.taipeizoo.architecture.main.plant.paging.PlantPagingSource
import com.superyao.taipeizoo.model.Plant
import com.superyao.taipeizoo.repository.DataRepository
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