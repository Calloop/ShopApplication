package com.example.shopapplication

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.shopapplication.databinding.ActivityMainBinding
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(binding.root)

        val bottomMenu: BottomNavigationView = binding.bottomMenu

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        bottomMenu.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.cart_fragment -> bottomMenu.visibility = View.GONE
                R.id.details_fragment -> bottomMenu.visibility = View.GONE
                else -> bottomMenu.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)

    }

    fun changeCartProducts(productsCount: Int) {
        val badge = binding.bottomMenu.getOrCreateBadge(R.id.cart_fragment)

        if (productsCount == 0) {
            badge.clearNumber()
        } else {
            badge.number = productsCount
        }
    }

    fun changeBottomMenuVisibility(visibility: Int) {
        binding.bottomMenu.visibility = visibility
    }
}