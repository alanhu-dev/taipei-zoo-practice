package com.superyao.homework210709.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class PlantResponse(val result: Result? = null) {
    data class Result(
        val offset: Int? = null,
        val limit: Int? = null,
        val count: Int? = null,
        val sort: String? = null,
        val results: List<Plant>? = null
    )
}

@Parcelize
data class Plant(
    @SerializedName("\uFEFFF_Name_Ch")
    val fNameCh: String? = null,

    @SerializedName("F_pdf02_ALT")
    val fPdf02ALT: String? = null,

    @SerializedName("F_Name_En")
    val fNameEn: String? = null,

    @SerializedName("F_Voice01_URL")
    val fVoice01URL: String? = null,

    @SerializedName("F_Name_Latin")
    val fNameLatin: String? = null,

    @SerializedName("F_Pic04_URL")
    val fPic04URL: String? = null,

    @SerializedName("F_Summary")
    val fSummary: String? = null,

    @SerializedName("F_Brief")
    val fBrief: String? = null,

    @SerializedName("F_Location")
    val fLocation: String? = null,

    @SerializedName("F_pdf02_URL")
    val fPdf02URL: String? = null,

    @SerializedName("F_Voice01_ALT")
    val fVoice01ALT: String? = null,

    @SerializedName("F_Pic03_ALT")
    val fPic03ALT: String? = null,

    @SerializedName("F_Voice02_URL")
    val fVoice02URL: String? = null,

    @SerializedName("rank")
    val rank: Double? = null,

    @SerializedName("F_Voice02_ALT")
    val fVoice02ALT: String? = null,

    @SerializedName("F_Pic01_URL")
    val fPic01URL: String? = null,

    @SerializedName("F_Pic02_ALT")
    val fPic02ALT: String? = null,

    @SerializedName("F_Keywords")
    val fKeywords: String? = null,

    @SerializedName("F_Family")
    val fFamily: String? = null,

    @SerializedName("F_CID")
    val fCID: String? = null,

    @SerializedName("F_Pic01_ALT")
    val fPic01ALT: String? = null,

    @SerializedName("F_Pic02_URL")
    val fPic02URL: String? = null,

    @SerializedName("F_Update")
    val fUpdate: String? = null,

    @SerializedName("F_Voice03_URL")
    val fVoice03URL: String? = null,

    @SerializedName("F_Code")
    val fCode: String? = null,

    @SerializedName("F_Function＆Application")
    val fFunctionApplication: String? = null,

    @SerializedName("F_Voice03_ALT")
    val fVoice03ALT: String? = null,

    @SerializedName("F_Pic03_URL")
    val fPic03URL: String? = null,

    @SerializedName("F_Vedio_URL")
    val fVedioURL: String? = null,

    @SerializedName("_full_count")
    val fullCount: String? = null,

    @SerializedName("F_pdf01_ALT")
    val fPdf01ALT: String? = null,

    @SerializedName("F_AlsoKnown")
    val fAlsoKnown: String? = null,

    @SerializedName("F_pdf01_URL")
    val fPdf01URL: String? = null,

    @SerializedName("_id")
    val id: Int? = null,

    @SerializedName("F_Feature")
    val fFeature: String? = null,

    @SerializedName("F_Pic04_ALT")
    val fPic04ALT: String? = null,

    @SerializedName("F_Genus")
    val fGenus: String? = null,

    @SerializedName("F_Geo")
    val fGeo: String? = null
) : Parcelable