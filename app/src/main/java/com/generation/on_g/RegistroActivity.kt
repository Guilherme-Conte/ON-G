package com.generation.on_g

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RegistroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        if(supportActionBar != null){
            supportActionBar?.hide()
        }
    }
}