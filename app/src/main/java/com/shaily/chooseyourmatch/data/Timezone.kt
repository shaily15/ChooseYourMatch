package com.shaily.chooseyourmatch.data

import com.google.gson.annotations.SerializedName

data class Timezone (

	@SerializedName("offset") val offset : String,
	@SerializedName("description") val description : String
)