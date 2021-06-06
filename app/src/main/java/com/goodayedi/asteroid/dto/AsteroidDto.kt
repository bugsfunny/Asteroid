package com.goodayedi.asteroid.dto

import com.goodayedi.asteroid.entity.AsteroidEntity
import com.goodayedi.asteroid.model.Asteroid

fun List<Asteroid>.asDatabaseAsteroid(): Array<AsteroidEntity> {
    return map {
        AsteroidEntity(
            id = it.id,
            name = it.name,
            imgUrl = it.imgUrl,
            absolute_magnitude = it.absolute_magnitude,
            estimated_diameter_max = it.estimated_diameter_max,
            is_potentially_hazardous_asteroid = it.is_potentially_hazardous_asteroid,
            close_approach_date = it.close_approach_date,
            relative_velocity = it.relative_velocity,
            miss_distance = it.miss_distance,
        )
    }.toTypedArray()
}

fun List<AsteroidEntity>.asDomainModel(): List<Asteroid> {
    return map {
        Asteroid(
            id = it.id,
            name = it.name,
            imgUrl = it.imgUrl,
            absolute_magnitude = it.absolute_magnitude,
            estimated_diameter_max = it.estimated_diameter_max,
            is_potentially_hazardous_asteroid = it.is_potentially_hazardous_asteroid,
            close_approach_date = it.close_approach_date,
            relative_velocity = it.relative_velocity,
            miss_distance = it.miss_distance,
        )
    }
}