package com.example.capstone.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserResponse {

    @SerializedName("data")
    @Expose
    var data : User? = null

}