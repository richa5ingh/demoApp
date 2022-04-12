package com.astropics.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.astropics.db.dao.PicsDao
import com.astropics.db.entity.PicEntity

@Database(version = 1, entities = [PicEntity::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun picsDao(): PicsDao
}