package edu.iesam.marvelapp.features.superheroes.data.local.xml

import android.content.Context
import edu.iesam.marvelapp.core.cache.AppCache
import edu.iesam.marvelapp.features.superheroes.domain.SuperHero
import edu.iesam.marvelapp.R
import edu.iesam.marvelapp.core.serialization.JsonSerializer

/**
 * Conector para gestionar datos en un fichero XML a través de SharedPreferences.
 *
 */
class SuperHeroXmlLocalDataSource(
    private val context: Context,
    private val serializer: JsonSerializer<List<SuperHero>>,
    private val appCache: AppCache
) {

    private val keySuperHero: String = "superhero"
    private val ttl: Long = 5 * 60 * 1000

    //-- Me creo la fuente de datos xml: SharedPreferences.
    val sharedPref = context.getSharedPreferences(
        context.getString(R.string.local_source_superhero), Context.MODE_PRIVATE
    )

    /**
     * Método para guardar un listado de SuperHero.
     */
    fun save(superHeroes: List<SuperHero>) {
        //apply, let, run  Scope Function
        sharedPref.edit().apply {
            putString(keySuperHero, serializer.toJson(superHeroes))
            apply()
        }
        appCache.updateTime(keySuperHero)
    }

    fun findAll(): List<SuperHero> {
        if (!appCache.isExpired(keySuperHero, ttl)) {
            val jsonList: String? = sharedPref.getString(keySuperHero, null)
            jsonList?.let {
                return serializer.fromJson(it)
            }
        }
        return emptyList()
    }

    fun findById(id: String): SuperHero? {
        return findAll().find { it.id == id }
    }

    fun deleteById(id: String) {
        save(findAll().filterNot { it.id == id })
    }

    fun deleteAll() {
        sharedPref.edit().apply {
            remove(keySuperHero)
            apply()
        }
    }
}