package com.example.cryptotracker.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptotracker.R
import com.example.cryptotracker.appComponent
import com.example.cryptotracker.screens.common.ScreenNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var screenNavigator: ScreenNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
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