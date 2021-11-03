package com.example.testtask.viewmodel

import android.os.Build
import android.telephony.DisconnectCause.SERVER_ERROR
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.model.character.Character
import com.example.testtask.model.remote.RemoteDataSource
import com.example.testtask.model.State
import com.example.testtask.model.repository.RemoteRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListViewModel(
    private val liveData: MutableLiveData<State> = MutableLiveData(),
    private val remoteRepository: RemoteRepository = RemoteRepository(RemoteDataSource())
) : ViewModel() {

    fun getLiveData() = liveData

    fun getCharFromRemote(character: Character){
        liveData.value = State.Loading
        remoteRepository.getCharById(character.id)
    }

    fun getAllChar(){
        remoteRepository.getAllChar()



    }
}