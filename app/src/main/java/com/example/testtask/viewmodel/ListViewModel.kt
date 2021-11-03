package com.example.testtask.viewmodel

import android.os.Build
import android.telephony.DisconnectCause.SERVER_ERROR
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtask.model.Character
import com.example.testtask.model.RemoteDataSource
import com.example.testtask.model.State
import com.example.testtask.model.repository.RemoteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(
    private val liveData: MutableLiveData<State> = MutableLiveData(),
    private val remoteRepository: RemoteRepository = RemoteRepository(RemoteDataSource())
) : ViewModel() {

    fun getLiveData() = liveData

    fun getCharFromRemote(character: Character){
        liveData.value = State.Loading
        remoteRepository.getCharById(character.id, callbackList)
    }

    fun getListFromRemote(){}

    private val callbackList = object : Callback<Character>{
        @RequiresApi(Build.VERSION_CODES.O)
        override fun onResponse(call: Call<Character>, response: Response<Character>) {
            val serverResponse: Character? = response.body()
            liveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    val requestURL = response.toString().substringAfter("url=").replace("}","")
                    chekResponseList(serverResponse, requestURL)
                } else {
                    State.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<Character>, t: Throwable) {
            liveData.postValue(State.Error(Throwable(SERVER_ERROR)))
        }

    }

    private val callbackChar = object : Callback<Character> {
        @RequiresApi(Build.VERSION_CODES.O)
        override fun onResponse(call: Call<Character>, response: Response<Character>) {
            val serverResponse: Character? = response.body()
            liveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    chekResponseChar(serverResponse)
                } else {
                    State.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<Character>, t: Throwable) {
        }

    }

    private val callbackPages = object : Callback<> {
        override fun onResponse(call: Call<GenresDTO>, response: Response<GenresDTO>) {
            val serverResponse: GenresDTO? = response.body()
            liveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    chekResponsePages(serverResponse)
                } else {
                    State.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<Character>, t: Throwable) {
            liveData.postValue(State.Error(Throwable(SERVER_ERROR)))
        }

    }


}