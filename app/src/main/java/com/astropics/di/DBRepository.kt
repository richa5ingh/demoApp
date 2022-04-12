package com.astropics.di

import com.astropics.db.AppDatabase
import com.astropics.db.entity.PicEntity
import javax.inject.Inject

class DBRepository @Inject constructor(val appDatabase: AppDatabase) {

    suspend fun insertPic(pic: PicEntity) {
        appDatabase.picsDao().insert(pic)
    }

    suspend fun getPicByDate(dateString: String): PicEntity? {
        return appDatabase.picsDao().getPicByDate(dateString)
    }

    suspend fun toggleFavouritePic(id: Int, isFav: Int, favDate: String) {
        return appDatabase.picsDao().toggleFavourite(id, isFav, favDate)
    }

    fun getFavourites(): List<PicEntity> {
        return appDatabase.picsDao().getFavourites()
    }


}