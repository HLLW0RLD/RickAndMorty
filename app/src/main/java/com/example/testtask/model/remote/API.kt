package com.example.testtask.model

import com.example.testtask.model.character.Character
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface API {

    @GET("character/{character-id}")
    fun getById(
        @Path("character-id") characterId : Int
        ) : Call<Character>

    @GET("character")
    fun getAllChar() : Call<Character>
}
