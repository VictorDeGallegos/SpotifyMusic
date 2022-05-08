package com.example.music

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.activity_inicio.*


class Inicio : AppCompatActivity() {

    private lateinit var toogle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        toogle = ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string
            .close_drawer)
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nav_view.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_premium -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmenContainerview, PremiumFragment())
                        commit()
                    }
                }


                R.id.nav_ayuda -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmenContainerview, AyudaFragment())
                        commit()
                    }
                }

                R.id.nav_descarga -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmenContainerview, DescargaFragment())
                        commit()
                    }
                }



            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toogle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}