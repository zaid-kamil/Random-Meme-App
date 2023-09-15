package com.example.randommeme.model

import kotlinx.serialization.Serializable

@Serializable
data class MemeWrapper(
    val count: Int,
    val memes: List<Meme>
)
