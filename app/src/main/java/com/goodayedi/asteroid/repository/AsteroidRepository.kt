package com.goodayedi.asteroid.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.goodayedi.asteroid.database.AsteroidDatabase
import com.goodayedi.asteroid.dto.asDatabaseAsteroid
import com.goodayedi.asteroid.dto.asDomainModel
import com.goodayedi.asteroid.model.Asteroid
import com.goodayedi.asteroid.model.PictureOfTheDay
import com.goodayedi.asteroid.network.Nasa
import com.goodayedi.asteroid.utils.getNextSevenDaysFormattedDates
import com.goodayedi.asteroid.utils.parseAsteroid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AsteroidRepository(private val database: AsteroidDatabase) {
    private val nextSevenDays = getNextSevenDaysFormattedDates()
    private val today = nextSevenDays.first()
    val asteroids: LiveData<ArrayList<Asteroid>> =
        Transformations.map(database.asteroidDao.getAsteroids()) {
            it.asDomainModel()
        }

    suspend fun getPictureOfTheDay(): PictureOfTheDay {
        return Nasa.api.getPictureOfTheDay()
    }

    suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            val fromApiAsteroids = Nasa.api.getAsteroids(today)
            val asteroids = parseAsteroid(fromApiAsteroids)
            database.asteroidDao.insertAll(*asteroids.asDatabaseAsteroid())
        }
    }
}



