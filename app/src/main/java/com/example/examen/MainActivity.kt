package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var nombreEditText: EditText
    private lateinit var noControlEditText: EditText
    private lateinit var agregarButton: Button
    private lateinit var verAgendaButton: Button
    private lateinit var contadorTextView: TextView
    private lateinit var buscarEditText: EditText
    private lateinit var buscarButton: Button

    private val agendaList = ArrayList<Agenda>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nombreEditText = findViewById(R.id.nombreEditText)
        noControlEditText = findViewById(R.id.noControlEditText)
        agregarButton = findViewById(R.id.agregarButton)
        verAgendaButton = findViewById(R.id.verAgendaButton)
        contadorTextView = findViewById(R.id.contadorTextView)
        buscarEditText = findViewById(R.id.buscarEditText)
        buscarButton = findViewById(R.id.buscarButton)

        agregarButton.setOnClickListener {
            agregarContacto()
        }

        verAgendaButton.setOnClickListener {
            if (agendaList.isNotEmpty()) {
                val intent = Intent(this, MainActivityDetalles::class.java)
                val contacto = agendaList[0]
                intent.putExtra("nombreContacto", contacto.nombre)
                intent.putExtra("noControlContacto", contacto.nocontrol)
                startActivity(intent)
            } else {
                Toast.makeText(this, "La agenda está vacía", Toast.LENGTH_SHORT).show()
            }
        }


        buscarButton.setOnClickListener {
            buscarContacto()
        }
    }

    private fun agregarContacto() {
        val nombre = nombreEditText.text.toString()
        val noControl = noControlEditText.text.toString()

        if (nombre.isNotEmpty() && noControl.isNotEmpty()) {
            agendaList.add(Agenda(nombre, noControl))
            contadorTextView.text = "Contactos en la agenda: ${agendaList.size}"
            nombreEditText.text.clear()
            noControlEditText.text.clear()
        } else {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun buscarContacto() {
        val busqueda = buscarEditText.text.toString()

        if (busqueda.isNotEmpty()) {
            val contactoEncontrado = agendaList.find { it.nombre == busqueda || it.nocontrol == busqueda }

            if (contactoEncontrado != null) {
                val intent = Intent(this, MainActivityDetalles::class.java)
                intent.putExtra("nombreContacto", contactoEncontrado.nombre)
                intent.putExtra("noControlContacto", contactoEncontrado.nocontrol) // Asegúrate de que noControlContacto sea pasado correctamente
                startActivity(intent)
            } else {
                Toast.makeText(this, "Contacto no encontrado", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Ingrese un nombre o número de control para buscar", Toast.LENGTH_SHORT).show()
        }
    }

}
