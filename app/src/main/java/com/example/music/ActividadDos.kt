package com.example.music

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.music.databinding.ActivityActividadDosBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_actividad_dos.*
import java.time.MonthDay


class ActividadDos : AppCompatActivity() {

    private lateinit var binding:ActivityActividadDosBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityActividadDosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.login.setOnClickListener {
            val intent = Intent(this, ActividadUno::class.java)
            startActivity(intent)
        }
        binding.registrarme.setOnClickListener{
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()
            val confirmPass = binding.confirmPassEt.text.toString()
            val user = binding.userEt.text.toString()
            val birthday = binding.etDate.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty() && user
                    .isNotEmpty() && birthday.isNotEmpty()){
                if (pass == confirmPass){
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener{
                        if (it.isSuccessful){
                            val intent = Intent(this, ActividadUno::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Los campos vacios no están permitidos", Toast.LENGTH_SHORT).show()
            }
        }



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