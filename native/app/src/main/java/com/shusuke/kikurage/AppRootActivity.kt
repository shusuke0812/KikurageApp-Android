package com.shusuke.kikurage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class AppRootActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_root)
        setupToolbar()
    }

    private fun setupToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    fun setupToolbarTitle(id: Int) {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val toolbarTitleTextView = toolbar.findViewById<TextView>(R.id.toolbar_title)
        toolbarTitleTextView?.setText(id)
    }
}