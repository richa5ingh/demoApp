package com.astropics.di

import com.astropics.db.entity.PicEntity
import com.astropics.model.PicResponse
import java.text.SimpleDateFormat
import java.util.*


object Transformer {

    const val DATE_FORMAT = "yyyy-MM-dd"

    fun convertNetworkResponseToDbEntity(picResponse: PicResponse): PicEntity {
        return PicEntity(
            title = picResponse.title,
            picDate = picResponse.date,
            mediaType = picResponse.mediaType,
            url = picResponse.url,
            hdUrl = picResponse.hdUrl,
            explanation = picResponse.explanation,
            copyright = picResponse.copyright,
            isFavourite = 0,
            favouriteDate = ""
        )
    }

    fun format(date: Date?): String {
        return SimpleDateFormat(DATE_FORMAT).format(date)
    }

    fun format(date: String?): Date {
        return SimpleDateFormat(DATE_FORMAT).parse(date)
    }

    fun getTodaysDateInString(): String {
        return format(Calendar.getInstance().time)
    }

}