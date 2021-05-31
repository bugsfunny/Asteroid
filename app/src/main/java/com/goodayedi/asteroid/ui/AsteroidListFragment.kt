package com.goodayedi.asteroid.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.goodayedi.asteroid.databinding.FragmentAsteroidListBinding

class AsteroidListFragment : Fragment() {

    private val viewModel: AsteroidViewModel by viewModels()
    private var _binding: FragmentAsteroidListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAsteroidListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.asteroidList.adapter = AsteroidAdapter()
        return binding.root
    }
}