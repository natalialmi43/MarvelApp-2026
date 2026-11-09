package com.example.marvelapp.core.time

interface AppTime {
    fun currentTimetoLong() : Long
}

class LocalAppTime : AppTime{
    override fun currentTimetoLong(): Long {
        return System.currentTimeMillis()
    }
}