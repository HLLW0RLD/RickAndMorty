package com.example.testtask.model.repository

import com.example.testtask.model.CharacterPage
import com.example.testtask.model.character.Character
import com.example.testtask.model.remote.RemoteDataSource


class RemoteRepository (private val remote : RemoteDataSource){

    fun getCharById(id : Int): Character{
        return remote.getCharById(id)
    }

    fun getCharPage(page : Int): CharacterPage{
        return remote.getCharPage(page)
    }
}