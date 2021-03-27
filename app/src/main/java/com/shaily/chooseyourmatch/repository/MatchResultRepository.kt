package com.shaily.chooseyourmatch.repository

import androidx.room.withTransaction
import com.shaily.chooseyourmatch.api.MatchResultsApi
import com.shaily.chooseyourmatch.db.MatchResultsDatabase
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
                matchResultsDao.deleteMatch(matchDetailsResponse)
                matchResultsDao.insertSelectedMatch(matchDetailsResponse)
            }

        }
    )
}