package com.example.marvelapp.core.serializacion

interface AppSerializaction {

    fun <T> toJson(obj: T): String


    fun <T> fromJson(json: String, classCasting: Class<T>)
}