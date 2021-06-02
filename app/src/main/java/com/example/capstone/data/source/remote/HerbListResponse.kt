package com.example.capstone.data.source.remote

import com.google.gson.annotations.SerializedName

data class HerbListResponse(

	@field:SerializedName("limit")
	val limit: Int? = null,

	@field:SerializedName("total_page")
	val totalPage: Int? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("data")
	val data:List<Herb>
)
