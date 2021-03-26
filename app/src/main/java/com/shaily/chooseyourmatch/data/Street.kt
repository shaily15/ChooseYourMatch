package com.shaily.chooseyourmatch.data

import com.google.gson.annotations.SerializedName
data class Street (

	@SerializedName("number") val number : Int? = 0,
	@SerializedName("name") val name : String
)