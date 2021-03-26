package com.shaily.chooseyourmatch.data

import com.google.gson.annotations.SerializedName

data class MatchDetailsResponse(
        @SerializedName("results") val results : List<Results>,
        @SerializedName("info") val info : Info
)