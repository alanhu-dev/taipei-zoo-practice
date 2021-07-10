package com.superyao.homework210709.repository

import com.superyao.homework210709.model.Pavilion
import com.superyao.homework210709.model.Plant

class DataRepository constructor(
    private val remoteDataSource: DataSource,
    private val localDataSource: DataSource,
) {
    fun getPavilions(
        query: String = "",
        limit: Int = 0,
        offset: Int = 0,
    ): List<Pavilion> {
        val pavilions = remoteDataSource.getPavilions(query, limit, offset)
        return if (pavilions.isNotEmpty()) {
            localDataSource.savePavilions(*pavilions.toTypedArray())
            pavilions
        } else {
            localDataSource.getPavilions()
        }
    }

    fun getPlants(
        query: String,
        limit: Int = 0,
        offset: Int = 0,
    ): List<Plant> {
        val plants = remoteDataSource.getPlants(query, limit, offset)
        return if (plants.isNotEmpty()) {
            localDataSource.savePlants(*plants.toTypedArray())
            plants
        } else {
            localDataSource.getPlants(query)
        }
    }
}