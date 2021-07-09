package com.superyao.homework210709.api

import com.superyao.homework210709.model.PavilionResponse
import com.superyao.homework210709.model.PlantResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val baseUrl = "https://data.taipei/api/v1/dataset/"
    }

    @GET("5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
    fun getPavilion(
        @Query("q") query: String = "",
        @Query("limit") limit: Int = 0,
        @Query("offset") offset: Int = 0,
    ): Call<PavilionResponse>

    @GET("f18de02f-b6c9-47c0-8cda-50efad621c14?scope=resourceAquire")
    fun getPlant(
        @Query("q") query: String,
        @Query("limit") limit: Int = 0,
        @Query("offset") offset: Int = 0,
    ): Call<PlantResponse>
}