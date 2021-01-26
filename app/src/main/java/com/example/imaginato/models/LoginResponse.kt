package com.example.imaginato.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("errorMessage")
	val errorMessage: String,

	@field:SerializedName("errorCode")
	val errorCode: String,

	@field:SerializedName("user")
	val user: User
)

data class User(

	@field:SerializedName("userName")
	val userName: String,

	@field:SerializedName("userId")
	val userId: String
)
