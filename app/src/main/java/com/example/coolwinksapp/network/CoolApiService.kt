package com.example.coolwinksapp.network

import com.example.coolwinksapp.model.CoolApiResponse
import retrofit2.Call
import retrofit2.http.GET


interface CoolApiService {
    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    @GET("posts")
    fun getUsersMessageData(): Call<List<CoolApiResponse>>
}