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

    private var _asteroids = MutableLiveData<ArrayList<Asteroid>>()
    val asteroids: LiveData<ArrayList<Asteroid>> get() = _asteroids
    private var _pictureOfTheDay = MutableLiveData<PictureOfTheDay>()
    val pictureOfTheDay: LiveData<PictureOfTheDay> get() = _pictureOfTheDay
    init {
        getAsteroidList()
        getPictureOfTheDay()
    }

    private fun getAsteroidList() {
        viewModelScope.launch {
            _asteroids.value = asteroidRepository.getAsteroids()
        }
    }
    private fun getPictureOfTheDay() {
        viewModelScope.launch {
            _pictureOfTheDay.value = asteroidRepository.getPictureOfTheDay()
        }
    }
}