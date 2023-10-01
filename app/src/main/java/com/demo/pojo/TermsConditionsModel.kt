package com.demo.pojo

import com.google.gson.annotations.SerializedName

data class TermsConditionsModel(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("body")
	val body: List<TermsBodyItem?>? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class TermsBodyItem(

	@field:SerializedName("upated_at")
	val upatedAt: Any? = null,

	@field:SerializedName("summary_data")
	val summaryData: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("content_data")
	val contentData: String? = null
)
