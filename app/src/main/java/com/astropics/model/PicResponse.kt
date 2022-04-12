package com.astropics.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PicResponse(
    val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("date") val date: String?,
    @SerializedName("media_type") val mediaType: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("hdurl") val hdUrl: String?,
    @SerializedName("explanation") val explanation: String?,
    @SerializedName("copyright") val copyright: String?
) : Parcelable
