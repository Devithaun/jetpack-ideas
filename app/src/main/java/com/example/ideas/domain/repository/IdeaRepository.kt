package com.example.ideas.domain.repository

import com.example.ideas.data.model.FunIdea

interface IdeaRepository {
    suspend fun fetchIdeas(): List<FunIdea>
}