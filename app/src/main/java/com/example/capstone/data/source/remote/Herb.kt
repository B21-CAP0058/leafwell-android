package com.example.capstone.data.source.remote

import com.google.gson.annotations.SerializedName

data class Herb(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("recipt")
	val recipt: String? = null,

	@field:SerializedName("uuid")
	val uuid: String? = null,

	@field:SerializedName("efficacy")
	val efficacy: String? = null
)
