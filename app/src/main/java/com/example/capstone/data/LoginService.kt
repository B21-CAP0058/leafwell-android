package com.example.capstone.data


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface LoginService {

    @FormUrlEncoded
    @POST("login")
    fun userLogin(
        @Body userReq : UserRequest
    ): Call<UserResponse>


}