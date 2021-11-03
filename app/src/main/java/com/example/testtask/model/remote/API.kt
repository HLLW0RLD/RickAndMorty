package com.example.testtask.model.remote

import com.example.testtask.model.character.Character
import com.example.testtask.model.character.CharacterList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {

    @GET("character/{character-id}")
    fun getById(
        @Path("character-id") characterId : Int
        ) : CharacterList

    @GET("character")
    fun getAllChar() : CharacterList

    @GET("api/character")
    fun getCharByName(@Query("name") name : String): CharacterList
}
