package com.goodayedi.asteroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.goodayedi.asteroid.model.Asteroid

class DetailViewModel(asteroid: Asteroid): ViewModel() {
    private val _selectedProperty = MutableLiveData<Asteroid>()
    val selectedProperty: LiveData<Asteroid>
        get() = _selectedProperty

    // Initialize the _selectedProperty MutableLiveData
    init {
        _selectedProperty.value = asteroid
    }
}
