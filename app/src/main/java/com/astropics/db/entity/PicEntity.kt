package com.astropics.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "picture_table")
data class PicEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "picDate") val picDate: String?,
    @ColumnInfo(name = "mediaType") val mediaType: String?,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "hdUrl") val hdUrl: String?,
    @ColumnInfo(name = "explanation") val explanation: String?,
    @ColumnInfo(name = "copyright") val copyright: String?,
    @ColumnInfo(name = "isFavourite") val isFavourite: Int?,
    @ColumnInfo(name = "favouriteDate") val favouriteDate: String?
)