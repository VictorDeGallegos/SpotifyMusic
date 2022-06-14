package com.example.music

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.music.databinding.ActivityActividadDosBinding
import com.example.music.firebase.user.UserInfo
import com.example.music.util.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_actividad_dos.*


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityActividadDosBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityActividadDosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.registrarme.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()
            val confirmPass = binding.confirmPassEt.text.toString()
            val user = binding.userEt.text.toString()
            val birthday = binding.etDate.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty() && user
                    .isNotEmpty() && birthday.isNotEmpty()
            ) {
                if (pass == confirmPass) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { registerResult ->
                        if (registerResult.isSuccessful) {
                            saveUserFirestore(email, pass, user, birthday, registerResult.result.user?.uid.toString())
                        } else {
                            Toast.makeText(this, registerResult.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Los campos vacios no están permitidos", Toast.LENGTH_SHORT)
                    .show()
            }
        }



        login.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    LoginActivity::class.java
                )
            ) //Referenciar esta actividad con la primera para regresar a login
        }

        etDate.setOnClickListener { showDatePickerDialog() }
    }

    private fun saveUserFirestore(
        email: String,
        pass: String,
        user: String,
        birthday: String,
        userId: String
    ) {
        val newUser = UserInfo(
            email = email,
            pass = pass,
            name = user,
            birthday = birthday,
            authId = userId
        )

        val db = Firebase.firestore // inicialización de DB
        db.collection(Constants.FIRESTORE_KEY_USER).add(newUser)
            .addOnSuccessListener { documentReference ->
                Log.d("Firestore", "DocumentSnapshot written with ID: ${documentReference.id}")
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error adding document", e)
            }

    }

    //Funcion que ejecute el DatePicker para elegir la fecha de cumpleaños
    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year ->
            onDateSelected(
                day,
                month,
                year
            )
        } //Variables en el orden que queremos recibir
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        val date = "$day/${month + 1}/$year"
        etDate.setText(date)
    }

}