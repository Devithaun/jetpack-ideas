package com.example.ideas.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ideas.data.repository.IdeaRepositoryImpl
import com.example.ideas.domain.usecase.GenerateRandomIdeaUseCase

class IdeaViewModelFactory(context: Context) : ViewModelProvider.Factory {
    private val repository = IdeaRepositoryImpl(context)
    private val useCase = GenerateRandomIdeaUseCase(repository)

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return IdeaViewModel(useCase) as T
    }
}