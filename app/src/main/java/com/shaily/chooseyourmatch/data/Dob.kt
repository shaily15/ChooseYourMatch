package com.shaily.chooseyourmatch.data

import com.google.gson.annotations.SerializedName

data class Dob (
	@SerializedName("date") val date : String,
	@SerializedName("age") val age : Int
)