package com.example.marvelapp.features.superheroes.data

import com.example.marvelapp.features.superheroes.data.local.xml.SuperHeroXmlLocalDataSource
import com.example.marvelapp.features.superheroes.domain.SuperHero
import com.example.marvelapp.features.superheroes.domain.SuperHeroRepository

class SuperHeroDataRepository (private val superHeroXmlLocalDataSource: SuperHeroXmlLocalDataSource): SuperHeroRepository {
    override fun getAll(): List<SuperHero> {
        return superHeroXmlLocalDataSource.findAll()
    }
}