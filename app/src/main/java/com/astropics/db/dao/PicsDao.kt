package com.astropics.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.astropics.db.entity.PicEntity

@Dao
interface PicsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pic: PicEntity)

    @Query("UPDATE picture_table SET isFavourite = :isFav, favouriteDate = :favDate where id = :id")
    suspend fun toggleFavourite(id: Int, isFav: Int, favDate: String)

    @Query("SELECT * FROM picture_table WHERE isFavourite = 1")
    fun getFavourites(): List<PicEntity>

    @Query("SELECT * FROM picture_table where picDate = :dateString LIMIT 1")
    suspend fun getPicByDate(dateString: String): PicEntity?
}