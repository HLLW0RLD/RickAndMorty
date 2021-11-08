package com.example.testtask.model.remote

import com.example.testtask.model.CharacterPage
import com.example.testtask.model.character.Character
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {

    @GET("character/{character-id}")
    fun getById(
        @Path("character-id") characterId : Int
        ) : Character


    @GET("character")
    fun getPage(
        @Query("page") pageIndex: Int
    ): CharacterPage
}
