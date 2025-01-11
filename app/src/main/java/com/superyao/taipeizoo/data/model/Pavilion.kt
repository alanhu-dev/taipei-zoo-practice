package com.superyao.taipeizoo.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "pavilion")
@Parcelize
data class Pavilion(
    @PrimaryKey
    @SerializedName("_id")
    val id: Int,

    @SerializedName("e_no")
    val eNo: String? = null,

    @SerializedName("e_category")
    val eCategory: String? = null,

    @SerializedName("e_name")
    val eName: String? = null,

    @SerializedName("e_pic_url")
    val ePicURL: String? = null,

    @SerializedName("e_info")
    val eInfo: String? = null,

    @SerializedName("e_memo")
    val eMemo: String? = null,

    @SerializedName("e_geo")
    val eGeo: String? = null,

    @SerializedName("e_url")
    val eURL: String? = null
) : Parcelable {
    val safePicURL: String
        get() = ePicURL?.replace("http://", "https://").orEmpty()
}