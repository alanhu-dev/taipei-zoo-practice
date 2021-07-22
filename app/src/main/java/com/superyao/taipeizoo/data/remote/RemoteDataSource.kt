package com.superyao.taipeizoo.data.remote

import com.superyao.taipeizoo.data.DataSource
import com.superyao.taipeizoo.data.model.Pavilion
import com.superyao.taipeizoo.data.model.Plant
import timber.log.Timber

class RemoteDataSource(private val apiService: ApiService) : DataSource {

    override fun loadPavilions(
        query: String,
        limit: Int,
        offset: Int
    ): DataSource.Result<List<Pavilion>> {
        return try {
            val response = apiService.getPavilion(query, limit, offset).execute()
            val pavilions = response.body()?.result?.results
            DataSource.Result(pavilions ?: listOf(), response.isSuccessful)
        } catch (e: Exception) { // simple error catching
            Timber.e(e)
            DataSource.Result(listOf(), false)
        }
    }

    override fun loadPlants(
        query: String,
        limit: Int,
        offset: Int
    ): DataSource.Result<List<Plant>> {
        return try {
            val response = apiService.getPlant(query, limit, offset).execute()
            val plants = response.body()?.result?.results
            DataSource.Result(plants ?: listOf(), response.isSuccessful)
        } catch (e: Exception) {
            Timber.e(e)
            DataSource.Result(listOf(), false)
        }
    }
}