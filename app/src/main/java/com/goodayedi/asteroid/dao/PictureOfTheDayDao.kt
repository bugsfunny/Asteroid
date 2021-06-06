package com.goodayedi.asteroid.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.goodayedi.asteroid.model.PictureOfTheDay

@Dao
interface PictureOfTheDayDao {
    @Query("SELECT * FROM pictureofthedayentity WHERE id = 1")
    fun getPictureOfTheDay(): LiveData<PictureOfTheDay>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pictureOfTheDay: PictureOfTheDay)
}