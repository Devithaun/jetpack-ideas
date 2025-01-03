package com.example.ideas.domain.usecase

import com.example.ideas.data.model.FunIdea
import com.example.ideas.domain.repository.IdeaRepository

class GenerateRandomIdeaUseCase(private val repository: IdeaRepository) {
    suspend operator fun invoke(): FunIdea {
        val ideas = repository.fetchIdeas()
        return ideas.randomOrNull() ?: FunIdea("Something went wrong")
    }
}