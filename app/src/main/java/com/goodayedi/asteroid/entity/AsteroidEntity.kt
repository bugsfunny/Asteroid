package com.goodayedi.asteroid.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AsteroidEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val imgUrl: String,
    val absolute_magnitude: Double,
    val estimated_diameter_max: Double,
    val is_potentially_hazardous_asteroid: Boolean,
    val close_approach_date: String,
    val relative_velocity: Double,
    val miss_distance: Double,
)
