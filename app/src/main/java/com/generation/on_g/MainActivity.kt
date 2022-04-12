package com.generation.on_g

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(supportActionBar != null){
            supportActionBar?.hide()
        }

        val nome = intent.getStringExtra("NOME")

        val textGuarda = findViewById<TextView>(R.id.textGuarda)
        if (nome != null) {

            textGuarda.text = "$nome"

        }
    }
}
