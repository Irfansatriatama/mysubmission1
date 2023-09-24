package com.irfan.mysubmission1.data.response

import com.google.gson.annotations.SerializedName

data class FollowResponse(
	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("login")
	val login: String,
)