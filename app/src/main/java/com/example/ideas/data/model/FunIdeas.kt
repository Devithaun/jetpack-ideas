package com.example.ideas.data.model

import com.google.gson.annotations.SerializedName

data class FunIdeas(@SerializedName("ideas") val ideas: List<FunIdea>)