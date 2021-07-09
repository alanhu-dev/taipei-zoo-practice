package com.superyao.homework210709.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class PavilionResponse(val result: Result? = null) {
    data class Result(
        val offset: Int? = null,
        val limit: Int? = null,
        val count: Int? = null,
        val sort: String? = null,
        val results: List<Pavilion>? = null
    )
}

@Parcelize
data class Pavilion(
    @SerializedName("E_Pic_URL")
    val ePicURL: String? = null,

    @SerializedName("E_Info")
    val eInfo: String? = null,

    @SerializedName("E_Category")
    val eCategory: String? = null,

    @SerializedName("_full_count")
    val fullCount: String? = null,

    @SerializedName("rank")
    val rank: Double? = null,

    @SerializedName("E_Memo")
    val eMemo: String? = null,

    @SerializedName("E_no")
    val eNo: String? = null,

    @SerializedName("E_Name")
    val eName: String? = null,

    @SerializedName("_id")
    val id: Int? = null,

    @SerializedName("E_URL")
    val eURL: String? = null,

    @SerializedName("E_Geo")
    val eGeo: String? = null
) : Parcelable