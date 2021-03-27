package com.shaily.chooseyourmatch.models

import com.google.gson.annotations.SerializedName

data class MatchDetailsResponse(
        @SerializedName("results") val results : List<Results>,
)