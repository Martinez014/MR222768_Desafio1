package com.example.mr222768_desafio1

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class SalarioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salario)

        val etNombre = findViewById<EditText>(R.id.etNombreEmpleado)
        val etSalario = findViewById<EditText>(R.id.etSalario)
        val btnCalcular = findViewById<Button>(R.id.btnCalcularSalario)
        val tvResultado = findViewById<TextView>(R.id.tvResultadoSalario)
        val btnVolver = findViewById<Button>(R.id.btnVolverSalario)

        btnCalcular.setOnClickListener {

            if (etNombre.text.isEmpty() || etSalario.text.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val salario = etSalario.text.toString().toDouble()

            if (salario <= 0) {
                Toast.makeText(this, "El salario debe ser positivo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val afp = salario * 0.0725
            val isss = salario * 0.03
            val renta = calcularRenta(salario)

            val totalDescuentos = afp + isss + renta
            val salarioNeto = salario - totalDescuentos

            val df = DecimalFormat("#.00")

            tvResultado.text =
                "Empleado: ${etNombre.text}\n\n" +
                        "Salario Base: $${df.format(salario)}\n" +
                        "AFP (7.25%): $${df.format(afp)}\n" +
                        "ISSS (3%): $${df.format(isss)}\n" +
                        "Renta: $${df.format(renta)}\n\n" +
                        "Total Descuentos: $${df.format(totalDescuentos)}\n" +
                        "Salario Neto: $${df.format(salarioNeto)}"
        }

        btnVolver.setOnClickListener {
            finish()
        }
    }

    private fun calcularRenta(salario: Double): Double {

        return when {
            salario <= 472.00 -> 0.0

            salario <= 895.24 ->
                ((salario - 472.00) * 0.10) + 17.67

            salario <= 2038.10 ->
                ((salario - 895.24) * 0.20) + 60.00

            else ->
                ((salario - 2038.10) * 0.30) + 288.57
        }
    }
}