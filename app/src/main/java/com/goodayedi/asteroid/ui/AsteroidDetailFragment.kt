package com.goodayedi.asteroid.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.goodayedi.asteroid.R
import com.goodayedi.asteroid.databinding.FragmentAsteroidDetailBinding
import com.goodayedi.asteroid.viewmodel.DetailViewModel
import com.goodayedi.asteroid.viewmodel.DetailViewModelFactory
import timber.log.Timber

class AsteroidDetailFragment : Fragment() {
    private var _binding: FragmentAsteroidDetailBinding? = null
    private val binding get() = _binding!!
    private val args : AsteroidDetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAsteroidDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        val asteroid = args.asteroid
        Timber.i("$asteroid")
        val factory = DetailViewModelFactory(asteroid)
        binding.viewModel = ViewModelProvider(this, factory).get(DetailViewModel::class.java)
        binding.helpIcon.setOnClickListener {
            displayAstronomicalUnitExplanationDialog()
        }
        return binding.root
    }

    private fun displayAstronomicalUnitExplanationDialog() {
        val builder = AlertDialog.Builder(requireActivity())
            .setMessage(getString(R.string.astronomica_unit_explanation))
            .setPositiveButton(android.R.string.ok, null)
        builder.create().show()
    }
}