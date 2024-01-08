package com.shusuke.kikurage.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.shusuke.kikurage.screen.home.HomeActivity
import com.shusuke.kikurage.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AppRootActivity : AppCompatActivity() {
    private lateinit var  viewModel: AppRootViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_root)

        viewModel = ViewModelProvider(this@AppRootActivity)[AppRootViewModel::class.java]
        setupToolbar()

        lifecycleScope.launch {
            viewModel.uiState.collect() { uiState ->
                if (uiState.isLogin) showHomeScreen()
            }
        }
    }

    // Call this method on each fragments
    fun setupToolbarTitle(id: Int) {
        supportActionBar?.setTitle(id)
    }

    private fun setupToolbar() {
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.auth_navigation_host_fragment)
        val navController =navHostFragment?.findNavController()
        navController?.let {
            val appBarConfiguration = AppBarConfiguration(it.graph)
            setupActionBarWithNavController(it, appBarConfiguration)
        }
    }

    private fun showHomeScreen() {
        val intentToHome = Intent(this@AppRootActivity, HomeActivity::class.java)
        startActivity(intentToHome)
    }
}