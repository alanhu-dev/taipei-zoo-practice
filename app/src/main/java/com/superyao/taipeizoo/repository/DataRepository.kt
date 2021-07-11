package com.superyao.taipeizoo.repository

import android.app.Application
import android.widget.Toast
import com.superyao.taipeizoo.R
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
            Toast.makeText(
                application,
                application.getString(R.string.request_failed_load_db),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    suspend fun loadPavilions(
        query: String = "",
        limit: Int = 0,
        offset: Int = 0,
    ): List<Pavilion> {
        val result = remoteDataSource.loadPavilions(query, limit, offset) // remote first
        return if (result.isSuccess) {
            if (result.data.isNotEmpty()) {
                localDataSource.savePavilions(*result.data.toTypedArray()) // store to db
            }
            result.data
        } else { // if failed, get from local
            toastRemoteError()
            localDataSource.loadPavilions(query, limit, offset).data
        }
    }

    suspend fun loadPlants(
        query: String,
        limit: Int = 0,
        offset: Int = 0,
    ): List<Plant> {
        val result = remoteDataSource.loadPlants(query, limit, offset)
        return if (result.isSuccess) {
            if (result.data.isNotEmpty()) {
                localDataSource.savePlants(*result.data.toTypedArray())
            }
            result.data
        } else {
            toastRemoteError()
            localDataSource.loadPlants(query, limit, offset).data
        }
    }
}