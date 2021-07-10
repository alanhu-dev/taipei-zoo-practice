package com.superyao.homework210709.repository

import com.superyao.homework210709.api.ApiService
import com.superyao.homework210709.model.Pavilion
import com.superyao.homework210709.model.Plant

class DataRepository constructor(private val apiService: ApiService) {
    fun getPavilion(
        query: String = "",
        limit: Int = 0,
        offset: Int = 0,
    ): List<Pavilion> {
        val response = apiService.getPavilion(query, limit, offset).execute()
        val pavilions = response.body()?.result?.results
        return pavilions ?: listOf()
    }

    fun getPlant(
        query: String,
        limit: Int = 0,
        offset: Int = 0,
    ): List<Plant> {
        val response = apiService.getPlant(query, limit, offset).execute()
        val plants = response.body()?.result?.results
        return plants ?: listOf()
    }
}