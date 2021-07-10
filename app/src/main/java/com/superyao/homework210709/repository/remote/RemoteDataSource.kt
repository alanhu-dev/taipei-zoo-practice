package com.superyao.homework210709.repository.remote

import com.superyao.homework210709.model.Pavilion
import com.superyao.homework210709.model.Plant
import com.superyao.homework210709.repository.DataSource

class RemoteDataSource(private val apiService: ApiService) : DataSource {
    override fun getPavilions(query: String, limit: Int, offset: Int): List<Pavilion> {
        val response = apiService.getPavilion(query, limit, offset).execute()
        val pavilions = response.body()?.result?.results
        return pavilions ?: listOf()
    }

    override fun getPlants(query: String, limit: Int, offset: Int): List<Plant> {
        val response = apiService.getPlant(query, limit, offset).execute()
        val plants = response.body()?.result?.results
        return plants ?: listOf()
    }
}