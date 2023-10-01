package com.demo.pojo

import com.google.gson.annotations.SerializedName

data class PrivacyPolicyModel(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("body")
	val body: List<PrivacyBody?>? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class PrivacyBody(

	@field:SerializedName("text")
	val text: String? = null
)
