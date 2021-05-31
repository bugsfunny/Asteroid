package com.goodayedi.asteroid.repository

import com.goodayedi.asteroid.domain.Asteroid
import com.goodayedi.asteroid.network.Nasa
import org.json.JSONObject

val asteroidRepository: AsteroidRepository by lazy {
    AsteroidRepository()
}

class AsteroidRepository {
    suspend fun getAsteroids(): ArrayList<Asteroid> {
        val asteroidList = ArrayList<Asteroid>()
        val response = Nasa.api.getAsteroids()
        val jsonResponse = JSONObject(response).getJSONObject("near_earth_objects")
        jsonResponse.keys().forEach {
            val asteroids = jsonResponse.getJSONArray(it)
            for (i in 0 until asteroids.length()) {
                val asteroidJson = asteroids[i] as JSONObject
                val id = asteroidJson.getLong("id")
                val codename = asteroidJson.getString("name")
                val imgUrl = asteroidJson.getString("nasa_jpl_url")
                val absoluteMagnitude = asteroidJson.getDouble("absolute_magnitude_h")
                val estimatedDiameter = asteroidJson.getJSONObject("estimated_diameter")
                    .getJSONObject("kilometers").getDouble("estimated_diameter_max")
                val closeApproachData = asteroidJson
                    .getJSONArray("close_approach_data").getJSONObject(0)
                val relativeVelocity = closeApproachData.getJSONObject("relative_velocity")
                    .getDouble("kilometers_per_second")
                val distanceFromEarth = closeApproachData.getJSONObject("miss_distance")
                    .getDouble("astronomical")
                val isPotentiallyHazardous = asteroidJson
                    .getBoolean("is_potentially_hazardous_asteroid")
                val asteroid = Asteroid(
                    id,
                    codename,
                    imgUrl,
                    absoluteMagnitude,
                    estimatedDiameter,
                    isPotentiallyHazardous,
                    it,
                    relativeVelocity,
                    distanceFromEarth
                )
                asteroidList.add(asteroid)
            }
        }
        return asteroidList
    }
}

