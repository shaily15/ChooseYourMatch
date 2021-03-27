package com.shaily.chooseyourmatch.api

import com.shaily.chooseyourmatch.models.MatchDetailsResponse
import retrofit2.Response
import retrofit2.http.GET

interface MatchResultsApi {

    companion object{
        const val BASE_URL = "https://randomuser.me/api/"
    }

    @GET("?results=10")
    suspend fun getMatchDetails(): Response<MatchDetailsResponse>
}