package com.superyao.homework210709.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.superyao.homework210709.model.Pavilion
import com.superyao.homework210709.model.Plant
import kotlinx.coroutines.launch

class DataRepository(
    private val application: Application,
    private val remoteDataSource: DataSource,
    private val localDataSource: DataSource,
) {
    private fun toastRemoteError() {
        ProcessLifecycleOwner.get().lifecycleScope.launch {
            Toast.makeText(application, "請求失敗，將讀取自 Database", Toast.LENGTH_SHORT).show()
        }
    }

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
            toastRemoteError()
            localDataSource.getPavilions(query, limit, offset)
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
            toastRemoteError()
            localDataSource.getPlants(query, limit, offset)
        }
    }
}