package com.goodayedi.asteroid.database

import android.content.Context
import androidx.room.*
import com.goodayedi.asteroid.dao.AsteroidDao
import com.goodayedi.asteroid.dao.PictureOfTheDayDao
import com.goodayedi.asteroid.entity.AsteroidEntity
import com.goodayedi.asteroid.entity.PictureOfTheDayEntity

@Database(entities = [AsteroidEntity::class, PictureOfTheDayEntity::class], version = 1)
abstract class AsteroidDatabase : RoomDatabase() {
    abstract val asteroidDao: AsteroidDao
    abstract val pictureOfTheDayDao: PictureOfTheDayDao
}

private lateinit var INSTANCE: AsteroidDatabase

fun getDatabase(context: Context): AsteroidDatabase {
    synchronized(AsteroidDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AsteroidDatabase::class.java, "asteroid"
            ).build()
        }
    }
    return INSTANCE
}