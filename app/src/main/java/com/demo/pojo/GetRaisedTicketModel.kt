package com.demo.pojo

import com.google.gson.annotations.SerializedName

data class GetRaisedTicketModel(

	@field:SerializedName("data")
	val data: RaisedTicketData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class RaisedTicketData(

	@field:SerializedName("tickets")
	val tickets: List<TicketsItem?>? = null
)

data class TicketsItem(

	@field:SerializedName("answer")
	val answer: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)
