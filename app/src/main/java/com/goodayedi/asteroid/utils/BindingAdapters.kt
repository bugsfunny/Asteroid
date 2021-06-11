package com.goodayedi.asteroid.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.goodayedi.asteroid.R
import com.goodayedi.asteroid.model.Asteroid
import com.goodayedi.asteroid.model.PictureOfTheDay
import com.goodayedi.asteroid.ui.AsteroidAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Asteroid>?) {
    val adapter = recyclerView.adapter as AsteroidAdapter
    adapter.submitList(data)
}

@BindingAdapter("hazardousImg")
fun loadImage(imageView: ImageView, hazardous: Boolean){
    if (hazardous){
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
    }
}

@BindingAdapter("asteroidStatusImage")
fun loadStatusImage(imageView: ImageView, hazardous: Boolean){
    if (hazardous){
        imageView.setImageResource(R.drawable.asteroid_hazardous)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
    }
}

@BindingAdapter("pictureOfTheDay")
fun loadPictureOfTheDay(imageView: ImageView, picture: PictureOfTheDay?) {
    picture?.let {
        if (picture.media_type != "image") {
            imageView.setImageResource(R.drawable.placeholder_picture_of_day)
        } else {
            Picasso.get().load(picture.url).into(imageView)
        }
    }

}