package com.shaily.chooseyourmatch.api

import com.shaily.chooseyourmatch.models.MatchDetailsResponse
import retrofit2.Response
import retrofit2.http.GET

interface MatchResultsApi {

    @GET("?results=10")
    suspend fun getMatchDetails(): Response<MatchDetailsResponse>
}