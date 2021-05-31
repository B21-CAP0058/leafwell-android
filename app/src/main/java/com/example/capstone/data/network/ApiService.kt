package com.example.capstone.data.network

import com.example.capstone.data.source.remote.DetalResponse
import com.example.capstone.data.source.remote.HerbListResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("api/v1/herbs")
    fun getHerbList():Call<HerbListResponse>

    @GET("/api/v1/herb/{uuid}")
    fun getHerbDetail(
        @Path("uuid")uuid:String
    ):Call<DetalResponse>

    @GET("api/v1/search-herb?")
    fun search(
        @Query("keyword")keyword:String
    ):Call<HerbListResponse>
}