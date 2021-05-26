package com.example.capstone.data


import retrofit2.Call
import retrofit2.http.*



interface LoginService {

    @FormUrlEncoded
    @POST("users/")
    fun userLogin(
      @Field("email") email : String,
      @Field("username") username : String
    ): Call<UserResponse>


}