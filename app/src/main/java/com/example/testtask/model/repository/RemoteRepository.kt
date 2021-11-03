package com.example.testtask.model.repository

import com.example.testtask.model.character.Character
import com.example.testtask.model.remote.RemoteDataSource
import retrofit2.Callback


class RemoteRepository (private val remote : RemoteDataSource){

    fun getCharById(id : Int){
        remote.getCharById(id)
    }

    fun getAllChar(){
        remote.getAllChar()
    }

    fun getCharByName(name : String){
        remote.getCharByName(name)
    }
}