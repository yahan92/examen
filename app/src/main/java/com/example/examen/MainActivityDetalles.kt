package com.example.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivityDetalles : AppCompatActivity() {

    private lateinit var nombreContactoTextView: TextView
    private lateinit var salirButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_detalles)

        nombreContactoTextView = findViewById(R.id.nombreContactoTextView)
        salirButton = findViewById(R.id.salirButton)

        val nombreContacto = intent.getStringExtra("nombreContacto")
        val noControlContacto = intent.getStringExtra("noControlContacto")

        nombreContacto?.let {
            nombreContactoTextView.text = "Nombre: $it\nNo. de Control: $noControlContacto"
        }

        salirButton.setOnClickListener {
            finish()
        }
    }
}