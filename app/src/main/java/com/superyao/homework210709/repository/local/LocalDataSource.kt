package com.superyao.homework210709.repository.local

import com.superyao.homework210709.model.Pavilion
import com.superyao.homework210709.model.Plant
import com.superyao.homework210709.repository.DataSource

class LocalDataSource(private val dataBase: DataBase) : DataSource {
    override fun getPavilions(query: String, limit: Int, offset: Int): List<Pavilion> {
        return dataBase.pavilionDao().getPavilions()
    }

    override fun getPlants(query: String, limit: Int, offset: Int): List<Plant> {
        return dataBase.plantDao().getPlants(query)
    }

    override fun savePavilions(vararg pavilion: Pavilion): LongArray {
        return dataBase.pavilionDao().insert(*pavilion)
    }

    override fun savePlants(vararg plant: Plant): LongArray {
        return dataBase.plantDao().insert(*plant)
    }
}