package com.example.testtask.model


import com.example.testtask.model.character.Character

data class CharacterPage(
    val info : Info,
    val result : List<Character> = emptyList()
) {

    data class Info(
        val count: Int = 0,
        val next: String? = null,
        val pages: Int = 0,
        val prev: String? = null
    )
}