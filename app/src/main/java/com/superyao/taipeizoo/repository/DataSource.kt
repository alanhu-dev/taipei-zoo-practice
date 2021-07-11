package com.superyao.taipeizoo.repository

import com.superyao.taipeizoo.model.Pavilion
import com.superyao.taipeizoo.model.Plant

interface DataSource {

    data class Result<T>(
        val data: T,
        val isSuccess: Boolean = true,
    )

    fun getPavilions(
        query: String = "",
        limit: Int = 0,
        offset: Int = 0
    ): Result<List<Pavilion>>

    fun getPlants(
        query: String = "",
        limit: Int = 0,
        offset: Int = 0
    ): Result<List<Plant>>

    fun savePavilions(vararg pavilion: Pavilion): LongArray {
        return longArrayOf()
    }

    fun savePlants(vararg plant: Plant): LongArray {
        return longArrayOf()
    }
}