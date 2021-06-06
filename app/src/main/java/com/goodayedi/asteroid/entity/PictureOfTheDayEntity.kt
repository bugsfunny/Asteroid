package com.goodayedi.asteroid.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PictureOfTheDayEntity(
    @PrimaryKey
    val id: Long,
    val url: String,
    val media_type: String,
    val title: String,
)
