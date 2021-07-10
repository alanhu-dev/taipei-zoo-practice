package com.superyao.homework210709.repository

import com.superyao.homework210709.model.Pavilion
import com.superyao.homework210709.model.Plant

interface DataSource {
    fun getPavilions(query: String = "", limit: Int = 0, offset: Int = 0): List<Pavilion>

    fun getPlants(query: String = "", limit: Int = 0, offset: Int = 0): List<Plant>

    fun savePavilions(vararg pavilion: Pavilion): LongArray {
        return longArrayOf()
    }

    fun savePlants(vararg plant: Plant): LongArray {
        return longArrayOf()
    }
}