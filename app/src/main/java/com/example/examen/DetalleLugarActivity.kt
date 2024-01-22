package com.example.examen

import android.R
import android.content.Intent.getIntent
import android.os.Bundle
import android.widget.TextView


class DetalleLugarActivity : AppCompatActivity() {
    protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_lugar)
        val lugarSeleccionado = getIntent().getStringExtra("LUGAR_SELECCIONADO")
        val detalleTextView: TextView = findViewById(R.id.detalle_text_view)
        detalleTextView.text = lugarSeleccionado
    }
}