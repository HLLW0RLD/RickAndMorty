package com.example.testtask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.testtask.model.character.Character
import com.example.testtask.model.factory.PageInfoFactory
import com.example.testtask.model.remote.RemoteDataSource
import com.example.testtask.model.repository.RemoteRepository
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    private val remoteRepository = RemoteRepository(RemoteDataSource())

    private val pageInfoFactory = PageInfoFactory(viewModelScope, remoteRepository)

    private val pageListConfig: PagedList.Config = PagedList.Config.Builder()
        .setPageSize(20)
        .setPrefetchDistance(40)
        .build()

    private val liveData: LiveData<PagedList<Character>> =
        LivePagedListBuilder(
            pageInfoFactory,
            pageListConfig
        ).build()

    fun getLiveData() = liveData


}