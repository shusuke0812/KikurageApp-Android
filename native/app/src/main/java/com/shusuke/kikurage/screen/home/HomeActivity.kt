package com.shusuke.kikurage.screen.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import com.shusuke.kikurage.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupToolbar()
        setupDrawerItemSelectedListner()
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
                R.id.wifiSelectDeviceFragment -> supportActionBar?.setTitle(R.string.menu_wifi_select_device_title)
            }
        }
    }

    private fun setupDrawerItemSelectedListner() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener {
            var fragment: Fragment? = null
            when (it.itemId) {
                R.id.drawer_menu_wifi_setting -> { transitionToMenuItem(R.id.action_homeDrawer_to_wifiSelectDeviceFragment) }
            }
            drawerLayout.closeDrawer(navigationView)
            true
        }
    }

    private fun transitionToMenuItem(id: Int) {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.home_navigation_host_fragment)
        val navController = navHostFragment?.findNavController()
        navController?.navigate(id)
    }
}