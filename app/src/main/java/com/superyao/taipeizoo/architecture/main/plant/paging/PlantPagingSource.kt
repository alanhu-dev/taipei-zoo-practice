package com.superyao.taipeizoo.architecture.main.plant.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.superyao.taipeizoo.model.Plant
import com.superyao.taipeizoo.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlantPagingSource(
    private val repository: DataRepository,
    private val query: String,
) : PagingSource<Int, Plant>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Plant> {
        return try {
            val pageNumber = params.key ?: 0
            val response = withContext(Dispatchers.IO) {
                // pageSize = 20
                // 1st: limit = 20, offset = 20 * 0 -> id: 1 ~ 20
                // 2nd: limit = 20, offset = 20 * 1 -> id: 21 ~ 40
                // ... -> id: 41 ~ 60
                // ...
                repository.loadPlants(query, pageSize, pageSize * pageNumber)
            }
            val nextKey = if (response.size >= pageSize) pageNumber + 1 else null
            LoadResult.Page(response, null, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Plant>): Int? = null

    companion object {
        const val pageSize = 20
    }
}