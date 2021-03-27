package com.shaily.chooseyourmatch.models

import com.google.gson.annotations.SerializedName
data class Street (

	@SerializedName("number") val number : Int? = 0,
	@SerializedName("name") val name : String
)