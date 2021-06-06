package com.goodayedi.asteroid.repository

import com.goodayedi.asteroid.database.AsteroidDatabase
import com.goodayedi.asteroid.model.Asteroid
import com.goodayedi.asteroid.model.PictureOfTheDay
import com.goodayedi.asteroid.network.Nasa
import com.goodayedi.asteroid.utils.parseAsteroid
import org.json.JSONObject
import timber.log.Timber

class AsteroidRepository(private val database: AsteroidDatabase) {
    suspend fun getAsteroids(): ArrayList<Asteroid> {
        val response = Nasa.api.getAsteroids()
        return parseAsteroid(response)
    }
    suspend fun getPictureOfTheDay(): PictureOfTheDay {
        return Nasa.api.getPictureOfTheDay()
    }

    fun refreshAsteroids() {
        Timber.i("Hello")
    }
}



