package com.shaily.chooseyourmatch.db

import androidx.room.*
import com.shaily.chooseyourmatch.models.MatchDetailsResponse
import com.shaily.chooseyourmatch.models.Results
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

@Dao
interface MatchResultsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSelectedMatch(result: Response<MatchDetailsResponse>)

    @Query("SELECT * FROM matchResults")
    fun getAllMatches(): Flow<List<Results>>

    @Delete
    suspend fun deleteMatch(result: Response<MatchDetailsResponse>)
}