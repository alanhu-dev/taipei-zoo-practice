package com.superyao.taipeizoo.data.remote

import com.superyao.taipeizoo.data.model.PavilionResponse
import com.superyao.taipeizoo.data.model.PlantResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val baseUrl = "https://data.taipei/api/v1/dataset/"
    }

    @GET("9683ba26-109e-4cb8-8f3d-03d1b349db9f?scope=resourceAquire")
    fun getPavilion(
        @Query("q") query: String = "",
        @Query("limit") limit: Int = 0,
        @Query("offset") offset: Int = 0,
    ): Call<PavilionResponse>

    @GET("e20706d8-bf89-4e6a-9768-db2a10bb2ba4?scope=resourceAquire")
    fun getPlant(
        @Query("q") query: String,
        @Query("limit") limit: Int = 0,
        @Query("offset") offset: Int = 0,
    ): Call<PlantResponse>
}