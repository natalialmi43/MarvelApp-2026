package com.example.marvelapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.marvelapp.features.superheroes.data.local.xml.SuperHeroXmlLocalDataSource
import com.example.marvelapp.features.superheroes.domain.SuperHero

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Crear un fichero SharedPreferences para verlo
        val xml = SuperHeroXmlLocalDataSource(this)
        xml.save(mutableListOf(SuperHero("id", "SuperMan", "super-man", "https://")))
        xml.save(mutableListOf(SuperHero("id2", "AAA", "AAA", "http://A")))


        val superHero = xml.findById("id2")
        Log.d(MainActivity::class.java.simpleName, superHero.toString())
    }
}