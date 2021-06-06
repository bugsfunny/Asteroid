package com.goodayedi.asteroid.utils

import com.goodayedi.asteroid.model.Asteroid
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

const val API_QUERY_DATE_FORMAT = "YYYY-MM-dd"
const val DEFAULT_END_DATE_DAYS = 7

fun parseAsteroid(response: String): ArrayList<Asteroid> {
    val asteroidList = ArrayList<Asteroid>()
    val nearEarthObjectsJson = JSONObject(response).getJSONObject("near_earth_objects")
    val nextSevenDaysFormattedDates = getNextSevenDaysFormattedDates()

    for (formattedDate in nextSevenDaysFormattedDates) {
        val dateAsteroidJsonArray = nearEarthObjectsJson.getJSONArray(formattedDate)

        for (i in 0 until dateAsteroidJsonArray.length()) {
            val asteroidJson = dateAsteroidJsonArray.getJSONObject(i)
            val id = asteroidJson.getLong("id")
            val codename = asteroidJson.getString("name")
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
                absoluteMagnitude,
                estimatedDiameter,
                isPotentiallyHazardous,
                formattedDate,
                relativeVelocity,
                distanceFromEarth
            )
            asteroidList.add(asteroid)
        }
    }
    return asteroidList
}

fun getNextSevenDaysFormattedDates(): ArrayList<String> {
    val formattedDateList = ArrayList<String>()

    val calendar = Calendar.getInstance()
    for (i in 0..DEFAULT_END_DATE_DAYS) {
        val currentTime = calendar.time
        val dateFormat = SimpleDateFormat(API_QUERY_DATE_FORMAT, Locale.getDefault())
        formattedDateList.add(dateFormat.format(currentTime))
        calendar.add(Calendar.DAY_OF_YEAR, 1)
    }

    return formattedDateList
}