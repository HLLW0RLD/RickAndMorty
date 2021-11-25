package com.example.testtask.model.remote

import com.example.testtask.model.character.Character
import com.example.testtask.model.character.CharacterList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {

    @GET("character/{character-id}")
    suspend fun getById(
        @Path("character-id") characterId : Int
        ) : Character


    @GET("character")
    suspend fun getPage(
        @Query("page") pageIndex: Int
    ): CharacterList
}
