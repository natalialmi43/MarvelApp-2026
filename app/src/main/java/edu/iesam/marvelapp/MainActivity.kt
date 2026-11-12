package edu.iesam.marvelapp

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import edu.iesam.marvelapp.core.cache.AppCache
import edu.iesam.marvelapp.core.cache.AppCacheTTL
import edu.iesam.marvelapp.core.serialization.KotlinJsonSerializer
import edu.iesam.marvelapp.core.time.LocalAppTime
import edu.iesam.marvelapp.features.superheroes.data.SuperHeroDataRepository
import edu.iesam.marvelapp.features.superheroes.data.local.xml.SuperHeroXmlLocalDataSource
import edu.iesam.marvelapp.features.superheroes.domain.GetSuperHeroesUseCase
import edu.iesam.marvelapp.features.superheroes.domain.SuperHero
import edu.iesam.marvelapp.features.superheroes.presentation.SuperHeroListViewModel

class MainActivity : AppCompatActivity() {


    private val viewModel: SuperHeroListViewModel by viewModels{
        SuperHeroListViewModel.factory(
            GetSuperHeroesUseCase(
                SuperHeroDataRepository(
                    SuperHeroXmlLocalDataSource(
                        this,
                        KotlinJsonSerializer.create(),
                        AppCacheTTL(this, LocalAppTime())
                    )
                )
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.d("MainActiviy", "OnCreate")

        initObserver()

        viewModel.loadSuperHeroes()

    }

    private fun initObserver(){
        viewModel.uiState.observe(this){ uiState ->
            Log.d ("MainActiviy", uiState.isLoading.toString())
            bind(uiState)
        }

    }


    private fun bind(uiState: SuperHeroListViewModel.UiState){

        if (uiState.isLoading){
            //show loading
        } else{
            // hide loading
        }

        if (uiState.hasError){
            //show error view
        }else{
            //hide error view
        }

        uiState.superHeroes?.let { superHeroes ->
            if(superHeroes.isEmpty()){
                //muestro pantalla de listado vacio
            } else{
                renderUi(superHeroes.first())
            }

        }

    }

    private fun renderUi( superHero: SuperHero){

        val superHeroLayout = findViewById<ConstraintLayout>(R.id.superHero_layout)


        val labelNameSuperHero =superHeroLayout.findViewById<TextView>(R.id.superHero_name)
        labelNameSuperHero.text = superHero.name

        val labelSlugSuperHero = superHeroLayout.findViewById<TextView>(R.id.superHero_slug)
        labelSlugSuperHero.text = superHero.slug

    }


}