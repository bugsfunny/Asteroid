package com.goodayedi.asteroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.goodayedi.asteroid.repository.AsteroidRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: AsteroidRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AsteroidViewModel::class.java) -> AsteroidViewModel(
                repository
            )
            else -> throw IllegalArgumentException("Unexpected model class $modelClass")
        } as T
    }
}