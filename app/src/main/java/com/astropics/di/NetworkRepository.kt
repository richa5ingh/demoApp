package com.astropics.di

import com.astropics.api.PicApiResponse
import com.astropics.model.PicResponse
import com.astropics.utils.Constants
import retrofit2.Response
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    val picApiResponse: PicApiResponse
) {

    suspend fun getPicByDate(): Response<PicResponse> {
        return picApiResponse.getPicOfDay(true, Constants.API_KEY)
    }

}