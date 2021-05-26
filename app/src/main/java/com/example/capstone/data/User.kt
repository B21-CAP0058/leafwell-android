package com.example.capstone.data

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("date_joined")
    val dateJoined: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("last_login")
    val lastLogin: String?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("username")
    val username: String?
)


