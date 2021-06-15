package com.goodayedi.asteroid.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.goodayedi.asteroid.database.AsteroidDatabase
import com.goodayedi.asteroid.dto.asDatabaseModel
import com.goodayedi.asteroid.dto.asDomainModel
import com.goodayedi.asteroid.model.Asteroid
import com.goodayedi.asteroid.model.PictureOfTheDay
import com.goodayedi.asteroid.network.Nasa
import com.goodayedi.asteroid.utils.SharedPref
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
    val pictureOfTheDay: LiveData<PictureOfTheDay> =
        Transformations.map(database.pictureOfTheDayDao.getPictureOfTheDay()) {
            it?.asDomainModel()
        }

    suspend fun refreshData() {
        withContext(Dispatchers.IO) {
            val lastSave = SharedPref.read("last", "0")
            if (today != lastSave) {
                val fromApiAsteroids = Nasa.api.getAsteroids(today)
                val asteroids = parseAsteroid(fromApiAsteroids)
                database.asteroidDao.insertAll(*asteroids.asDatabaseModel())
                val pictureOfTheDay = Nasa.api.getPictureOfTheDay()
                database.pictureOfTheDayDao.insert(pictureOfTheDay.asDatabaseModel())
                SharedPref.write("last", today)
            }
        }
    }
}



