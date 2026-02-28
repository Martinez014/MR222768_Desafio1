package com.example.mr222768_desafio1

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class PromedioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promedio)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etNota1 = findViewById<EditText>(R.id.etNota1)
        val etNota2 = findViewById<EditText>(R.id.etNota2)
        val etNota3 = findViewById<EditText>(R.id.etNota3)
        val etNota4 = findViewById<EditText>(R.id.etNota4)
        val etNota5 = findViewById<EditText>(R.id.etNota5)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        btnCalcular.setOnClickListener {

            if (etNombre.text.isEmpty() ||
                etNota1.text.isEmpty() ||
                etNota2.text.isEmpty() ||
                etNota3.text.isEmpty() ||
                etNota4.text.isEmpty() ||
                etNota5.text.isEmpty()) {

                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val nota1 = etNota1.text.toString().toDouble()
            val nota2 = etNota2.text.toString().toDouble()
            val nota3 = etNota3.text.toString().toDouble()
            val nota4 = etNota4.text.toString().toDouble()
            val nota5 = etNota5.text.toString().toDouble()

            if (!validarNota(nota1) || !validarNota(nota2) ||
                !validarNota(nota3) || !validarNota(nota4) ||
                !validarNota(nota5)) {

                Toast.makeText(this, "Las notas deben estar entre 0 y 10", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val promedio = calcularPromedio(nota1, nota2, nota3, nota4, nota5)

            val df = DecimalFormat("#.00")
            val estado = if (promedio >= 6) "Aprobó" else "Reprobó"

            tvResultado.text = "Estudiante: ${etNombre.text}\n" +
                    "Promedio: ${df.format(promedio)}\n" +
                    "Resultado: $estado"
        }

        btnVolver.setOnClickListener {
            finish()
        }
    }

    private fun calcularPromedio(n1: Double, n2: Double, n3: Double, n4: Double, n5: Double): Double {
        return (n1 * 0.2) + (n2 * 0.2) + (n3 * 0.2) + (n4 * 0.2) + (n5 * 0.2)
    }

    private fun validarNota(nota: Double): Boolean {
        return nota in 0.0..10.0
    }
}