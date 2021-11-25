package com.example.testtask.model.repository

import com.example.testtask.model.character.Character
import com.example.testtask.model.character.CharacterList
import com.example.testtask.model.remote.RemoteDataSource


class RemoteRepository{

    suspend fun getChar(id : Int) : Character{
        return RemoteDataSource.client.getChar(id)
    }

    suspend fun getCharPage(page: Int): CharacterList {
        return RemoteDataSource.client.getCharPage(page)
    }
}