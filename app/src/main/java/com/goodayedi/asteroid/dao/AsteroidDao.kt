package com.goodayedi.asteroid.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.goodayedi.asteroid.entity.AsteroidEntity

@Dao
interface AsteroidDao {
    @Query("SELECT * FROM asteroidentity")
    fun getAsteroids(): LiveData<List<AsteroidEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg asteroid: AsteroidEntity)
}