package com.example.mr222768_desafio1

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import kotlin.math.pow
import kotlin.math.sqrt

class CalculadoraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)

        val et1 = findViewById<EditText>(R.id.etNumero1)
        val et2 = findViewById<EditText>(R.id.etNumero2)
        val tvResultado = findViewById<TextView>(R.id.tvResultadoCalc)
        val df = DecimalFormat("#.00")

        fun obtenerNumeros(): Pair<Double, Double>? {
            if (et1.text.isEmpty()) {
                Toast.makeText(this, "Ingrese al menos el número 1", Toast.LENGTH_SHORT).show()
                return null
            }
            val n1 = et1.text.toString().toDouble()
            val n2 = if (et2.text.isEmpty()) 0.0 else et2.text.toString().toDouble()
            return Pair(n1, n2)
        }

        findViewById<Button>(R.id.btnSumar).setOnClickListener {
            obtenerNumeros()?.let {
                tvResultado.text = "Resultado: ${df.format(it.first + it.second)}"
            }
        }

        findViewById<Button>(R.id.btnRestar).setOnClickListener {
            obtenerNumeros()?.let {
                tvResultado.text = "Resultado: ${df.format(it.first - it.second)}"
            }
        }

        findViewById<Button>(R.id.btnMultiplicar).setOnClickListener {
            obtenerNumeros()?.let {
                tvResultado.text = "Resultado: ${df.format(it.first * it.second)}"
            }
        }

        findViewById<Button>(R.id.btnDividir).setOnClickListener {
            obtenerNumeros()?.let {
                if (it.second == 0.0) {
                    Toast.makeText(this, "No se puede dividir entre cero", Toast.LENGTH_SHORT).show()
                } else {
                    tvResultado.text = "Resultado: ${df.format(it.first / it.second)}"
                }
            }
        }

        findViewById<Button>(R.id.btnExponente).setOnClickListener {
            obtenerNumeros()?.let {
                tvResultado.text = "Resultado: ${df.format(it.first.pow(it.second))}"
            }
        }

        findViewById<Button>(R.id.btnRaiz).setOnClickListener {
            if (et1.text.isEmpty()) {
                Toast.makeText(this, "Ingrese el número 1", Toast.LENGTH_SHORT).show()
            } else {
                val numero = et1.text.toString().toDouble()
                if (numero < 0) {
                    Toast.makeText(this, "No existe raíz real de número negativo", Toast.LENGTH_SHORT).show()
                } else {
                    tvResultado.text = "Resultado: ${df.format(sqrt(numero))}"
                }
            }
        }

        findViewById<Button>(R.id.btnVolverCalc).setOnClickListener {
            finish()
        }
    }
}