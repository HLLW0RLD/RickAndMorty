package com.example.testtask.model

import com.example.testtask.model.character.Character

sealed class State {
    object Loading : State()
    class Error(val error: Throwable) : State()
    class Success(val characterData: List<Character>) : State()
}