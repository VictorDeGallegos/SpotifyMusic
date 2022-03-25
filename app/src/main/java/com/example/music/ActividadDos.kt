package com.example.music

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_actividad_dos.*
import java.time.MonthDay


class ActividadDos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_dos)

        login.setOnClickListener{
            startActivity(Intent(this, ActividadUno::class.java)) //Referenciar esta actividad con la primera para regresar a login
        }

        etDate.setOnClickListener {showDatePickerDialog()}
    }

    //Funcion que ejecute el DatePicker para elegir la fecha de cumpleaños
    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment{day, month, year -> onDateSelected(day, month, year)} //Variables en el orden que queremos recibir
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun onDateSelected(day:Int, month:Int, year:Int){
        etDate.setText("Tu fecha de nacimiento es $day de $month del $year ")
    }
}