package com.astropics.api

import com.astropics.model.PicResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PicApiResponse {

    @GET("planetary/apod")
    suspend fun getPicOfDay(
        @Query("thumbs") thumbs: Boolean,
        @Query("api_key") apiKey: String,
    ): Response<PicResponse>
}
