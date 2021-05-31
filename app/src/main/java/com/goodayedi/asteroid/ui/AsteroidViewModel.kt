package com.goodayedi.asteroid.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodayedi.asteroid.domain.Asteroid
import com.goodayedi.asteroid.repository.asteroidRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class AsteroidViewModel: ViewModel() {
    private var _asteroids = MutableLiveData<ArrayList<Asteroid>>()
    val asteroids: LiveData<ArrayList<Asteroid>> get() = _asteroids
    init {
        getAsteroidList()
    }

    private fun getAsteroidList() {
        viewModelScope.launch {
            _asteroids.value = asteroidRepository.getAsteroids()
            Timber.i(asteroids.value.toString())
        }
    }
}