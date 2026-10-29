package com.example.marvelapp.features.superheroes.data.local.xml

import android.content.Context
import com.example.marvelapp.R
import com.example.marvelapp.core.serializacion.AppSerializaction
import com.example.marvelapp.features.superheroes.domain.SuperHero
import com.google.gson.Gson

/*
Conector para gestionar datos en un fichero XML a través de SharedPreferences.
 */

class SuperHeroXmlLocalDataSource (
    private val context: Context,
    private val serialization : AppSerializaction){
    //Context va a actuar como puente

    //Quito el activity (ya que no estamos en el main y necesitamos vincularlo) y le paso el context que tengo por constructor
    val sharedPref = context.getSharedPreferences(
        // Necesito darle context otra vez
        context.getString(R.string.local_source_superhero), Context.MODE_PRIVATE)




    public fun findAll(): List<SuperHero>{
        val superHero = SuperHero("1", "SW", "s-w", "https://...")

        val json =  serialization.toJson(superHero)

        serialization.fromJson(json, superHero::class.java)




        return emptyList()
    }



}