package com.example.music

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.music.databinding.ActivityMenuPrincipalBinding
import com.example.music.util.Constants
import com.google.firebase.auth.FirebaseAuth

class MenuPrincipal : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMenuPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMenuPrincipal.toolbar)


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_menu_principal)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                doLogOut()
                return false
            }
            R.id.settings -> {
                Toast.makeText(this, "Clicked Settings", Toast.LENGTH_SHORT).show()
                return false
            }
            R.id.faq -> {
                Toast.makeText(this, "Clicked FAQ", Toast.LENGTH_SHORT).show()
                return false
            }
            R.id.about -> {
                Toast.makeText(this, "Clicked About", Toast.LENGTH_SHORT).show()
                return false
            }
            else -> {
                return false
            }
        }
    }

    private fun doLogOut() {
        FirebaseAuth.getInstance().signOut()
        val sharedPref = getSharedPreferences(
            Constants.PREF_KEY, Context.MODE_PRIVATE)
        sharedPref.edit().clear().commit()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_menu_principal)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}