package com.demo.pojo

import com.google.gson.annotations.SerializedName

data class CommonModel(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)
