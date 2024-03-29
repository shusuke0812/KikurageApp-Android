package com.shusuke.kikurage.screen.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.shusuke.kikurage.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupToolbar()
    }

    private fun setupToolbar() {
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.home_navigation_host_fragment)
        val navController = navHostFragment?.findNavController()
        navController?.let {
            val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment), drawerLayout)
            toolbar.setupWithNavController(it, appBarConfiguration)
        }
        navController?.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.homeFragment -> supportActionBar?.setTitle(R.string.fragment_home_title)
            }
        }
    }
}