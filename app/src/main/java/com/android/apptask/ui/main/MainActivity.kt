package com.android.apptask.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.android.apptask.R
import com.android.apptask.databinding.ActivityMainBinding
import com.android.apptask.utils.gone
import com.android.apptask.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.actionBar.btnBack.setOnClickListener { onBackPressed() }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.actionBar.tvTitle.text = destination.label ?: ""

            when (destination.id) {
                R.id.fruitDetailsFragment -> {
                    binding.actionBar.btnBack.show()
                }
                else -> binding.actionBar.btnBack.gone()
            }

        }

    }
}