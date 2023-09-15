package com.example.randommeme.network

import com.example.randommeme.model.MemeWrapper
import retrofit2.http.GET
import retrofit2.http.Path

interface MemeApiService {
    @GET("gimme/{count}")
    suspend fun getMemes(
        @Path("count") count: Int
    ): MemeWrapper
}