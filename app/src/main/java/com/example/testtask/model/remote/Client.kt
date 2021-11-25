package com.example.testtask.model.remote

import com.example.testtask.model.character.Character
import com.example.testtask.model.character.CharacterList

class Client(private val api: API) {

    suspend fun getChar(id : Int) : Character {
        return api.getById(id)
    }

    suspend fun getCharPage(page: Int): CharacterList {
        return api.getPage(page)
    }
}
