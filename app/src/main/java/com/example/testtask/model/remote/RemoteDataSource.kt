package com.example.testtask.model

import com.example.testtask.model.character.Character
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build().create(API :: class.java)


    fun getCharById(id: Int, callback: Callback<Character>) {
       retrofit.getById(id).enqueue(callback)
    }

    fun getAllChar(){

    }
}