package com.example.testtask.model

import androidx.paging.PageKeyedDataSource
import com.example.testtask.model.character.Character
import com.example.testtask.model.repository.RemoteRepository
import com.example.testtask.viewmodel.ListViewModel
import kotlinx.coroutines.AbstractCoroutine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class PageInfo(val repository: RemoteRepository,
               val coroutine : CoroutineScope): PageKeyedDataSource<Int, Character>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Character>
    ) {
       coroutine.launch {
           val page = repository.getCharPage(1)
           callback.onResult(page.result, null, getNextIndex(page.info.next))
       }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        coroutine.launch {
            val page = repository.getCharPage(params.key)
            callback.onResult(page.result, getNextIndex(page.info.next))
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {

    }

    private fun getNextIndex(next : String?): Int?{
        return next?.split("?page=")?.get(1)?.toInt()
    }

}