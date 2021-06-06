package com.goodayedi.asteroid.dto

import com.goodayedi.asteroid.entity.PictureOfTheDayEntity
import com.goodayedi.asteroid.model.PictureOfTheDay

fun PictureOfTheDayEntity.asDomainModel(): PictureOfTheDay {
    return PictureOfTheDay(
        url = this.url,
        media_type = this.media_type,
        title = this.title
    )
}

fun PictureOfTheDay.asDatabaseModel(): PictureOfTheDayEntity {
    return PictureOfTheDayEntity(
        id = 1,
        url = this.url,
        media_type = this.media_type,
        title = this.title
    )
}