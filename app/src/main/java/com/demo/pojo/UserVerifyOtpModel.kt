package com.demo.pojo

import com.google.gson.annotations.SerializedName

data class UserVerifyOtpModel(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class Data(

	@field:SerializedName("isVerified")
	val isVerified: Int? = null,

	@field:SerializedName("loggedIn")
	val loggedIn: Int? = null,

	@field:SerializedName("error")
	val error: Int? = null,

	@field:SerializedName("message")
	val message: String? = null
)
