package com.goodayedi.asteroid.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.goodayedi.asteroid.database.AsteroidDatabase
import com.goodayedi.asteroid.database.getDatabase
import com.goodayedi.asteroid.databinding.FragmentAsteroidListBinding
import com.goodayedi.asteroid.repository.AsteroidRepository
import com.goodayedi.asteroid.viewmodel.AsteroidViewModel
import com.goodayedi.asteroid.viewmodel.ViewModelFactory

class AsteroidListFragment : Fragment() {

    private lateinit var database: AsteroidDatabase
    private lateinit var viewModel: AsteroidViewModel

    private var _binding: FragmentAsteroidListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAsteroidListBinding.inflate(inflater, container, false)
        database = getDatabase(requireContext())
        val asteroidRepository = AsteroidRepository(database)
        val factory = ViewModelFactory(asteroidRepository)
        viewModel = ViewModelProvider(this, factory).get(AsteroidViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.asteroidList.adapter = AsteroidAdapter()
        return binding.root
    }
}