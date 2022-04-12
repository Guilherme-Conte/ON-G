package com.generation.on_g.splashScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.generation.on_g.R
import com.generation.on_g.RegistroActivity


class SplashScreen: AppCompatActivity() {

    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        if (supportActionBar != null) {
            supportActionBar?.hide()
        }

        handler = Handler()
        handler.postDelayed({
            val intent = Intent(applicationContext, RegistroActivity::class.java)
            startActivity(intent)
            finish()
        }, 500)
    }
}