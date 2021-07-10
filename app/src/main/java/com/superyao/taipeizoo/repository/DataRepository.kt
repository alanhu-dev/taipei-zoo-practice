package com.superyao.taipeizoo.repository

import android.app.Application
import android.widget.Toast
import com.superyao.taipeizoo.model.Pavilion
import com.superyao.taipeizoo.model.Plant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataRepository(
    private val application: Application,
    private val remoteDataSource: DataSource,
    private val localDataSource: DataSource,
) {
    private suspend fun toastRemoteError() {
        withContext(Dispatchers.Main) {
            Toast.makeText(application, "請求失敗，將讀取自 Database", Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun getPavilions(
        query: String = "",
        limit: Int = 0,
        offset: Int = 0,
    ): List<Pavilion> {
        val pavilions = remoteDataSource.getPavilions(query, limit, offset)
        return if (pavilions.isNotEmpty()) {
            localDataSource.savePavilions(*pavilions.toTypedArray())
            pavilions
        } else {
            toastRemoteError()
            localDataSource.getPavilions(query, limit, offset)
        }
    }

    suspend fun getPlants(
        query: String,
        limit: Int = 0,
        offset: Int = 0,
    ): List<Plant> {
        val plants = remoteDataSource.getPlants(query, limit, offset)
        return if (plants.isNotEmpty()) {
            localDataSource.savePlants(*plants.toTypedArray())
            plants
        } else {
            toastRemoteError()
            localDataSource.getPlants(query, limit, offset)
        }
    }
}