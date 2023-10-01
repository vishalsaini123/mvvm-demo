package com.demo.pojo

import com.google.gson.annotations.SerializedName

data class GetListenerListingModel(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("body")
	val body: Body? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class ListenerDataItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("rating")
	val rating: Any? = null,

	@field:SerializedName("description")
	val description: Any? = null,

	@field:SerializedName("profile_picture")
	val profilePicture: Any? = null,

	@field:SerializedName("age")
	val age: Any? = null
)

data class Body(

	@field:SerializedName("listeners")
	val listeners: Listeners? = null
)

data class Listeners(

	@field:SerializedName("data")
	val data: List<ListenerDataItem?>? = null
)
