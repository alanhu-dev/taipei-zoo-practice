package com.superyao.taipeizoo.model

data class PlantResponse(val result: Result? = null) {
    data class Result(
        val offset: Int? = null,
        val limit: Int? = null,
        val count: Int? = null,
        val sort: String? = null,
        val results: List<Plant>? = null
    )
}