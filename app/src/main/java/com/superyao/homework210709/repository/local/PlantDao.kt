package com.superyao.homework210709.repository.local

import androidx.room.*
import com.superyao.homework210709.model.Plant

@Dao
abstract class PlantDao {
    @Query("SELECT COUNT(id) FROM plant")
    abstract fun getSize(): Int

    @Query("SELECT * FROM plant WHERE fLocation LIKE :pavilionName")
    abstract fun getPlants(pavilionName: String): List<Plant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg plant: Plant): LongArray

    @Update
    abstract fun update(vararg plant: Plant): Int

    @Delete
    abstract fun delete(vararg plant: Plant): Int
}