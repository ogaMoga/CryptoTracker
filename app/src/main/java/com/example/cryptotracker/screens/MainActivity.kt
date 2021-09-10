package com.example.cryptotracker.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptotracker.R
import com.example.cryptotracker.screens.common.ScreenNavigator
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val screenNavigator: ScreenNavigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        screenNavigator.init(supportFragmentManager, savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        screenNavigator.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        if (!screenNavigator.navigateUp()) {
            super.onBackPressed()
        }
    }
}