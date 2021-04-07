package com.shaily.chooseyourmatch.repository

import androidx.room.withTransaction
import com.shaily.chooseyourmatch.api.MatchResultsApi
import com.shaily.chooseyourmatch.db.MatchResultsDatabase
import com.shaily.chooseyourmatch.models.Results
import com.shaily.chooseyourmatch.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class MatchResultRepository @Inject constructor(
    private val api: MatchResultsApi,
    private val db: MatchResultsDatabase
) {
    private val matchResultsDao = db.getMatchResultsDao()

    fun getAllMatchResults() = networkBoundResource(
        query = {
            matchResultsDao.getAllMatches()
        },
        fetch = {
            delay(2000)
            api.getMatchDetails()
        },
        saveFetchResult = {
                matchDetailsResponse ->
            db.withTransaction {

                for(index in 0..matchDetailsResponse.body()?.results!!.size) {
                    matchResultsDao.insertAllMatch(matchDetailsResponse.body()?.results!!)
                }
            }

        }
    )

    suspend fun insertMatch(matchResult: Results) = db.getMatchResultsDao()
        .insertSelectedMatch(matchResult)

    suspend fun deleteMatch(matchResult: Results) = db.getMatchResultsDao()
        .deleteMatch(matchResult)
}