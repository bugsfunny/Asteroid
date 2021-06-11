package com.goodayedi.asteroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.goodayedi.asteroid.model.Asteroid

class DetailViewModelFactory(
    private val asteroid: Asteroid,
    ) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(asteroid) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}