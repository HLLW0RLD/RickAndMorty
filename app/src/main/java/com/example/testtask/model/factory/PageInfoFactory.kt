package com.example.testtask.model.factory

import androidx.paging.DataSource
import com.example.testtask.model.PageInfo
import com.example.testtask.model.character.Character
import com.example.testtask.model.repository.RemoteRepository
import kotlinx.coroutines.CoroutineScope


class PageInfoFactory(
    private val coroutine : CoroutineScope,
    private val repository: RemoteRepository) : DataSource.Factory<Int, Character>() {

    override fun create(): DataSource<Int, Character> {
        return PageInfo(repository, coroutine)
    }
}