package com.example.examen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class EditarLugarActivity : AppCompatActivity() {
    private var nombreEditText: EditText? = null
    private var direccionEditText: EditText? = null
    protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_lugar)
        val lugarSeleccionado = Intent.getIntent().getStringExtra("LUGAR_SELECCIONADO")
        nombreEditText = findViewById(R.id.editTextNombre)
        direccionEditText = findViewById(R.id.editTextDireccion)

        cargarInformacionLugar(lugarSeleccionado)
        val guardarButton: Button = findViewById(R.id.buttonGuardar)
        guardarButton.setOnClickListener { // Aquí puedes implementar la lógica para guardar los cambios en la base de datos o donde sea necesario
            guardarCambios()
        }
    }

    private fun cargarInformacionLugar(lugarSeleccionado: String?) {

    }

    private fun guardarCambios() {
        val nuevoNombre = nombreEditText!!.text.toString()
        val nuevaDireccion = direccionEditText!!.text.toString()

    }
}
