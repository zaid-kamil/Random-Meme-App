package com.example.randommeme.data

import android.util.Log
import com.example.randommeme.model.Meme
import com.example.randommeme.network.MemeApiService

interface MemeRepository {
    suspend fun getMemes(count: Int = 25): List<Meme>
}

class NetworkMemeRepository(
    private val memeApi: MemeApiService
) : MemeRepository {
    override suspend fun getMemes(count: Int): List<Meme> {
        Log.d("NetworkMemeRepository", "getMemes")
        return memeApi.getMemes(count).memes
    }
}