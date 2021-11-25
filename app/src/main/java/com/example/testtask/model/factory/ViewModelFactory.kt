package com.example.testtask.model.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.model.repository.RemoteRepository
import com.example.testtask.viewmodel.ListViewModel


class ViewModelFactory(private val repository: RemoteRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListViewModel(repository) as T
    }
}