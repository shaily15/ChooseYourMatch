package com.shaily.chooseyourmatch.db

import androidx.room.*
import com.shaily.chooseyourmatch.models.Results
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchResultsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSelectedMatch(results: List<Results>)

    @Query("SELECT * FROM matchResults")
    fun getAllMatches(): Flow<List<Results>>

    @Delete
    suspend fun deletedMatch(results: List<Results>)
}