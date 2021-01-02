package com.fob.beers.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.fob.beers.R


class SplashActivity : AppCompatActivity() {
    // Timer using Handler
    private val SPLASH_TIME = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startSplashTimer()
    }

    // Handling splash timer.
    private fun startSplashTimer() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIME.toLong())
    }
}