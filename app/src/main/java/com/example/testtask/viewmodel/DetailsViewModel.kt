package com.example.testtask.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.model.character.Character
import com.example.testtask.model.remote.RemoteDataSource
import com.example.testtask.model.repository.RemoteRepository
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private val remoteRepository = RemoteRepository(RemoteDataSource())

    private val liveData = MutableLiveData<Character>()

    fun getLiveData() = liveData

    fun getCharFromRemote(characterId: Int){
        viewModelScope.launch {
            val response = remoteRepository.getCharById(characterId)

        }
    }
}