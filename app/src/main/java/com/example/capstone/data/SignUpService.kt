package com.example.capstone.data

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SignUpService {

    @FormUrlEncoded
    @POST("users/")
    fun createUser(
        @Field("username") username: String,
        @Field("date_joined") date: String,
        @Field("password") password: String,
    ): Call<SignUpResponse>
}
