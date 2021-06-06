package com.goodayedi.asteroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodayedi.asteroid.model.Asteroid
import com.goodayedi.asteroid.model.PictureOfTheDay
import com.goodayedi.asteroid.repository.AsteroidRepository
import kotlinx.coroutines.launch

class AsteroidViewModel(private val asteroidRepository: AsteroidRepository): ViewModel() {

    val asteroids: LiveData<ArrayList<Asteroid>> = asteroidRepository.asteroids
    private var _pictureOfTheDay = MutableLiveData<PictureOfTheDay>()
    val pictureOfTheDay: LiveData<PictureOfTheDay> get() = _pictureOfTheDay
    init {
        getPictureOfTheDay()
    }

    private fun getPictureOfTheDay() {
        viewModelScope.launch {
            _pictureOfTheDay.value = asteroidRepository.getPictureOfTheDay()
        }
    }
}