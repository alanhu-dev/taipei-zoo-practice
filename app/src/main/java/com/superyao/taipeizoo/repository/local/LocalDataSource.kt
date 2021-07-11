package com.superyao.taipeizoo.repository.local

import com.superyao.taipeizoo.model.Pavilion
import com.superyao.taipeizoo.model.Plant
import com.superyao.taipeizoo.repository.DataSource

class LocalDataSource(private val dataBase: DataBase) : DataSource {
    override fun getPavilions(query: String, limit: Int, offset: Int): List<Pavilion> {
        return dataBase.pavilionDao().getPavilions()
    }

    override fun getPlants(query: String, limit: Int, offset: Int): List<Plant> {
        val wrappedQuery = "%$query%"
        return if (limit == 0) {
            dataBase.plantDao().getPlants(wrappedQuery)
        } else {
            dataBase.plantDao().getPagingPlants(wrappedQuery, limit, offset)
        }
    }

    override fun savePavilions(vararg pavilion: Pavilion): LongArray {
        return dataBase.pavilionDao().insert(*pavilion)
    }

    override fun savePlants(vararg plant: Plant): LongArray {
        return dataBase.plantDao().insert(*plant)
    }
}