package com.demo.pojo

import com.google.gson.annotations.SerializedName

data class UserRegistrationExtraModel(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("body")
	val body: List<BodyItem?>? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class BodyItem(

	@field:SerializedName("isapproved")
	val isapproved: Any? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("city")
	val city: Any? = null,

	@field:SerializedName("profile_picture")
	val profilePicture: Any? = null,

	@field:SerializedName("videointro")
	val videointro: Any? = null,

	@field:SerializedName("aboutme")
	val aboutme: Any? = null,

	@field:SerializedName("isverified")
	val isverified: Any? = null,

	@field:SerializedName("isprofilecompleted")
	val isprofilecompleted: Any? = null,

	@field:SerializedName("user_type")
	val userType: Any? = null,

	@field:SerializedName("interest")
	val interest: Any? = null,

	@field:SerializedName("name")
	val name: Any? = null,

	@field:SerializedName("phone_number")
	val phoneNumber: Any? = null,

	@field:SerializedName("state")
	val state: Any? = null,

	@field:SerializedName("age")
	val age: Any? = null
)
