package com.demo.pojo

import com.google.gson.annotations.SerializedName

data class UserSignUpModel(

	@field:SerializedName("data")
	val data: UserSignUPData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class UserSignUPData(

	@field:SerializedName("gender")
	val gender: Any? = null,

	@field:SerializedName("dob")
	val dob: Any? = null,

	@field:SerializedName("mobile")
	val mobile: String? = null,

	@field:SerializedName("session_id")
	val sessionId: String? = null,

	@field:SerializedName("Registerd")
	val registerd: Int? = null,

	@field:SerializedName("otp")
	val otp: String? = null,

	@field:SerializedName("error")
	val error: Int? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("categoryid")
	val categoryid: Any? = null,

	@field:SerializedName("token")
	val token: String? = null
)
