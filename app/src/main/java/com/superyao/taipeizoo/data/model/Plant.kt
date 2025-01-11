package com.superyao.taipeizoo.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "plant")
@Parcelize
data class Plant(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("_id")
    val id: Int? = null,

    @SerializedName("f_name_ch")
    val fNameCh: String? = null,

    @SerializedName("f_name_en")
    val fNameEn: String? = null,

    @SerializedName("f_name_latin")
    val fNameLatin: String? = null,

    @SerializedName("f_family")
    val fFamily: String? = null,

    @SerializedName("f_genus")
    val fGenus: String? = null,

    @SerializedName("f_summary")
    val fSummary: String? = null,

    @SerializedName("f_brief")
    val fBrief: String? = null,

    @SerializedName("f_feature")
    val fFeature: String? = null,

    @SerializedName("f_function＆application")
    val fFunctionApplication: String? = null,

    @SerializedName("f_keywords")
    val fKeywords: String? = null,

    @SerializedName("f_alsoknown")
    val fAlsoKnown: String? = null,

    @SerializedName("f_location")
    val fLocation: String? = null,

    @SerializedName("f_geo")
    val fGeo: String? = null,

    @SerializedName("f_pic01_url")
    val fPic01URL: String? = null,

    @SerializedName("f_pic01_alt")
    val fPic01ALT: String? = null,

    @SerializedName("f_pic02_url")
    val fPic02URL: String? = null,

    @SerializedName("f_pic02_alt")
    val fPic02ALT: String? = null,

    @SerializedName("f_pic03_url")
    val fPic03URL: String? = null,

    @SerializedName("f_pic03_alt")
    val fPic03ALT: String? = null,

    @SerializedName("f_pic04_url")
    val fPic04URL: String? = null,

    @SerializedName("f_pic04_alt")
    val fPic04ALT: String? = null,

    @SerializedName("f_pdf01_url")
    val fPdf01URL: String? = null,

    @SerializedName("f_pdf01_alt")
    val fPdf01ALT: String? = null,

    @SerializedName("f_pdf02_url")
    val fPdf02URL: String? = null,

    @SerializedName("f_pdf02_alt")
    val fPdf02ALT: String? = null,

    @SerializedName("f_voice01_url")
    val fVoice01URL: String? = null,

    @SerializedName("f_voice01_alt")
    val fVoice01ALT: String? = null,

    @SerializedName("f_voice02_url")
    val fVoice02URL: String? = null,

    @SerializedName("f_voice02_alt")
    val fVoice02ALT: String? = null,

    @SerializedName("f_voice03_url")
    val fVoice03URL: String? = null,

    @SerializedName("f_voice03_alt")
    val fVoice03ALT: String? = null,

    @SerializedName("f_vedio_url")
    val fVedioURL: String? = null,

    @SerializedName("f_update")
    val fUpdate: String? = null,

    @SerializedName("f_cid")
    val fCID: String? = null,

    @SerializedName("f_code")
    val fCode: String? = null
) : Parcelable {
    val safePic01URL: String
        get() = fPic01URL?.replace("http://", "https://").orEmpty()
}