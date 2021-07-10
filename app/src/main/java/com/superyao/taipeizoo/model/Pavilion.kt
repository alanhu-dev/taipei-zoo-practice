package com.superyao.taipeizoo.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "pavilion")
@Parcelize
data class Pavilion(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("_id")
    val id: Int? = null,

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

    @SerializedName("E_URL")
    val eURL: String? = null,

    @SerializedName("E_Geo")
    val eGeo: String? = null
) : Parcelable