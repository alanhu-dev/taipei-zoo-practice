package com.superyao.homework210709.architecture.main.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.superyao.homework210709.model.Plant
import com.superyao.homework210709.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlantPagingSource(
    private val repository: DataRepository,
    private val query: String,
) : PagingSource<Int, Plant>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Plant> {
        return try {
            // Start refresh at page 1 if undefined.
            val pageNumber = params.key ?: 0
            val response = withContext(Dispatchers.IO) {
                repository.getPlant(query, pageSize, pageSize * pageNumber)
            }
            val nextKey = if (response.size >= pageSize) pageNumber + 1 else null
            LoadResult.Page(response, null, nextKey)
        } catch (e: Exception) {
            // Handle errors in this block and return LoadResult.Error if it is an
            // expected error (such as a network failure).
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Plant>): Int? = null

    companion object {
        const val pageSize = 10
    }
}