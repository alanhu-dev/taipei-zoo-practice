package com.superyao.taipeizoo.repository.local

import com.superyao.taipeizoo.model.Pavilion
import com.superyao.taipeizoo.model.Plant
import com.superyao.taipeizoo.repository.DataSource

class LocalDataSource(private val dataBase: DataBase) : DataSource {

    override fun loadPavilions(
        query: String,
        limit: Int,
        offset: Int
    ): DataSource.Result<List<Pavilion>> {
        return DataSource.Result(dataBase.pavilionDao().getPavilions())
    }

    override fun loadPlants(
        query: String,
        limit: Int,
        offset: Int
    ): DataSource.Result<List<Plant>> {
        val wrappedQuery = "%$query%"
        return if (limit == 0) {
            DataSource.Result(dataBase.plantDao().getPlants(wrappedQuery))
        } else {
            DataSource.Result(dataBase.plantDao().getPagingPlants(wrappedQuery, limit, offset))
        }
    }

    override fun savePavilions(vararg pavilion: Pavilion): LongArray {
        return dataBase.pavilionDao().insert(*pavilion)
    }

    override fun savePlants(vararg plant: Plant): LongArray {
        return dataBase.plantDao().insert(*plant)
    }
}