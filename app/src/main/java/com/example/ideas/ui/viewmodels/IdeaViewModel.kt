package com.example.ideas.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ideas.data.model.FunIdea
import com.example.ideas.domain.api.UiState
import com.example.ideas.domain.usecase.GenerateRandomIdeaUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class IdeaViewModel(private val randomIdeaUseCase: GenerateRandomIdeaUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<FunIdea>>(UiState.Loading)
    val uiState: StateFlow<UiState<FunIdea>> get() = _uiState

    fun generateIdea() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = UiState.Loading
            try {
                val idea = randomIdeaUseCase.invoke()
                _uiState.value = UiState.Success(idea)
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to fetch Idea: ${e.message}")
            }
        }
    }
}