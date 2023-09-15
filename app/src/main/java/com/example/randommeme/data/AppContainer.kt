package com.example.randommeme.data

import com.example.randommeme.network.MemeApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val memeRepository: MemeRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://meme-api.com/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: MemeApiService by lazy {
        retrofit.create(MemeApiService::class.java)
    }

    override val memeRepository: MemeRepository by lazy {
        NetworkMemeRepository(retrofitService)
    }
}