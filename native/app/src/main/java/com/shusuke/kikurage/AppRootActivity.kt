package com.shusuke.kikurage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppRootActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_root)

        setupToolbar()
    }

    // Call this method on each fragments
    fun setupToolbarTitle(id: Int) {
        supportActionBar?.setTitle(id)
    }

    private fun setupToolbar() {
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navController = supportFragmentManager.findFragmentById(R.id.auth_navigation_host_fragment)
        navController?.findNavController()?.let {
            val appBarConfiguration = AppBarConfiguration(it.graph)
            toolbar.setupWithNavController(it, appBarConfiguration)
        }
    }
}