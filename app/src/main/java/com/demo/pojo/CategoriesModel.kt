package com.demo.pojo

import com.google.gson.annotations.SerializedName

data class CategoriesModel(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("body")
	val data: List<CategoriesBodyItem>? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class CategoriesBodyItem(

	@field:SerializedName("category_name")
	val categoryName: String? = null,

	@field:SerializedName("category_id")
	val categoryId: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: Any? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null
)
