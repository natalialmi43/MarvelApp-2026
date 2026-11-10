package edu.iesam.marvelapp

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import edu.iesam.marvelapp.features.superheroes.presentation.SuperHeroListViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: SuperHeroListViewModel by viewModels()

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
                // muestro el listado de superheroes
            }

        }

    }


}