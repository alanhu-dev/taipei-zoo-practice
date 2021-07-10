package com.superyao.homework210709.architecture.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.superyao.homework210709.architecture.main.paging.PlantPagingSource
import com.superyao.homework210709.model.Plant
import com.superyao.homework210709.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PlantAllViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {
    fun allImagesFlow(query: String = ""): Flow<PagingData<Plant>> {
        return Pager(PagingConfig(PlantPagingSource.pageSize)) {
            PlantPagingSource(repository, query)
        }.flow.cachedIn(viewModelScope)
    }
}