package com.goodayedi.asteroid.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PictureOfTheDay(
    val url: String,
    val media_type: String,
    val title: String,
) : Parcelable