package com.example.ideas.data.repository

import android.content.Context
import com.example.ideas.data.model.FunIdea
import com.example.ideas.data.model.FunIdeas
import com.example.ideas.domain.repository.IdeaRepository
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStream

class IdeaRepositoryImpl(private val context: Context) : IdeaRepository {

    override suspend fun fetchIdeas(): List<FunIdea> {
        return try {
            val inputStream: InputStream = context.assets.open("funideas.json")
            val bufferedReader: BufferedReader = BufferedReader(inputStream.reader())
            val jsonString = bufferedReader.use { it.readText() }

            val data = Gson().fromJson(jsonString, FunIdeas::class.java)
            data.ideas
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList<FunIdea>()
        }
    }
}