package com.example.music

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.music.databinding.ActivityActividadUnoBinding
import com.example.music.util.Constants
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityActividadUnoBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActividadUnoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.createaccount.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btlogin.setOnClickListener{
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener{
                    if (it.isSuccessful){
                        val sharedPref = getSharedPreferences(
                            Constants.PREF_KEY, Context.MODE_PRIVATE)
                        sharedPref.edit().putString(Constants.PREF_USERID, it.result.user?.uid
                            .toString()).commit()
                        val intent = Intent(this, MenuPrincipal::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, "Los campos vacios no están permitidos", Toast.LENGTH_SHORT).show()
            }
        }



    }
      override fun onStart() {
          super.onStart()

          if(firebaseAuth.currentUser != null){
              val intent = Intent(this, MenuPrincipal::class.java)
              startActivity(intent)
          }
      } //Metodo para hacer consistente el inicio de sesion al cerrar la app

    fun openBrowser(view: View) {

        //Get url from tag
        val url = view.getTag() as String
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.addCategory(Intent.CATEGORY_BROWSABLE)

        //pass the url to intent data
        intent.data = Uri.parse(url)
        startActivity(intent)
    }



}
