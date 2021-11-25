package com.example.testtask.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.model.character.Character
import com.example.testtask.model.repository.RemoteRepository
import kotlinx.coroutines.launch

class ListViewModel(private val repository: RemoteRepository) : ViewModel() {

    var listCharactersData = MutableLiveData<List<Character>>()

    var characterData = MutableLiveData<Character>()

    fun getCharacter(character: Character){
        viewModelScope.launch {
            val character = repository.getChar(character.id)
            characterData.value = character
        }
    }

    fun getCharacters(page: Int) {
        viewModelScope.launch{
            val characters = repository.getCharPage(page)
            listCharactersData.value = characters.results
        }
    }
}