package com.shusuke.kikurage.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
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

    private fun setupToolbar() {
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.auth_navigation_host_fragment)
        val navController =navHostFragment?.findNavController()
        navController?.let {
            val appBarConfiguration = AppBarConfiguration(it.graph)
            toolbar.setupWithNavController(it, appBarConfiguration)
        }
        navController?.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.topFragment -> supportActionBar?.setTitle(R.string.fragment_top_title)
                R.id.loginFragment -> supportActionBar?.setTitle(R.string.fragment_login_title)
            }
        }
    }

    private fun showHomeScreen() {
        val intentToHome = Intent(this@AppRootActivity, HomeActivity::class.java)
        startActivity(intentToHome)
    }
}