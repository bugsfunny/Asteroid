package com.goodayedi.asteroid.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Asteroid(
    val id: Long,
    val name: String,
    val absolute_magnitude: Double,
    val estimated_diameter_max: Double,
    val is_potentially_hazardous_asteroid: Boolean,
    val close_approach_date: String,
    val relative_velocity: Double,
    val miss_distance: Double,
) : Parcelable