package com.goodayedi.asteroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodayedi.asteroid.model.Asteroid
import com.goodayedi.asteroid.model.PictureOfTheDay
import com.goodayedi.asteroid.repository.AsteroidRepository
import kotlinx.coroutines.launch
import java.lang.Exception

enum class NasaApiStatus { LOADING, ERROR, DONE}

class AsteroidViewModel(private val asteroidRepository: AsteroidRepository): ViewModel() {

    val asteroids: LiveData<ArrayList<Asteroid>> = asteroidRepository.asteroids
    val pictureOfTheDay: LiveData<PictureOfTheDay> = asteroidRepository.pictureOfTheDay

    private val _navigateToSelectedProperty = MutableLiveData<Asteroid?>()
    val navigateToSelectedProperty: LiveData<Asteroid?>
        get() = _navigateToSelectedProperty

    private val _status = MutableLiveData<NasaApiStatus>()
    val status: LiveData<NasaApiStatus> get() = _status

    init {
        viewModelScope.launch {
            _status.value = NasaApiStatus.LOADING
            try {
                asteroidRepository.refreshData()
                _status.value = NasaApiStatus.DONE
            } catch (e: Exception){
                _status.value = NasaApiStatus.ERROR
            }
        }
    }

    fun displayPropertyDetails(asteroid: Asteroid) {
        _navigateToSelectedProperty.value = asteroid
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }
}