package com.superyao.taipeizoo.repository.remote

import com.superyao.taipeizoo.model.Pavilion
import com.superyao.taipeizoo.model.Plant
import com.superyao.taipeizoo.repository.DataSource
import timber.log.Timber

class RemoteDataSource(private val apiService: ApiService) : DataSource {

    override fun getPavilions(
        query: String,
        limit: Int,
        offset: Int
    ): DataSource.Result<List<Pavilion>> {
        return try {
            val response = apiService.getPavilion(query, limit, offset).execute()
            val pavilions = response.body()?.result?.results
            return DataSource.Result(pavilions ?: listOf())
        } catch (e: Exception) { // simple error catching
            Timber.e(e)
            DataSource.Result(listOf(), false)
        }
    }

    override fun getPlants(
        query: String,
        limit: Int,
        offset: Int
    ): DataSource.Result<List<Plant>> {
        return try {
            val response = apiService.getPlant(query, limit, offset).execute()
            val plants = response.body()?.result?.results
            DataSource.Result(plants ?: listOf())
        } catch (e: Exception) {
            Timber.e(e)
            DataSource.Result(listOf(), false)
        }
    }
}