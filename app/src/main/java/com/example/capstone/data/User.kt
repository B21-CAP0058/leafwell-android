package com.example.capstone.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {

    @SerializedName("data")
    @Expose
    var email: String?= null

    @SerializedName("password")
    @Expose
    var password: String? =null

    @SerializedName("token")
    @Expose
    var token: String? =null

}
