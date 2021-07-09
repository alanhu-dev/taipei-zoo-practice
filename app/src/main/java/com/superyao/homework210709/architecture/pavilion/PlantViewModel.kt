package com.superyao.homework210709.architecture.pavilion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.superyao.homework210709.architecture.pavilion.paging.PlantPagingSource
import com.superyao.homework210709.model.Plant
import com.superyao.homework210709.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantViewModel @Inject constructor(
    private val repository: DataRepository
): ViewModel() {

    fun allImagesFlow(
        query: String
    ): Flow<PagingData<Plant>> {
        return Pager(PagingConfig(PlantPagingSource.pageSize)) {
            PlantPagingSource(repository, query)
        }.flow.cachedIn(viewModelScope)
    }
}