package com.shaily.chooseyourmatch.db

import androidx.room.*
import com.shaily.chooseyourmatch.models.Results
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchResultsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMatch(result: List<Results>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSelectedMatch(result: Results)

    @Query("SELECT * FROM matchResults")
    fun getAllMatches(): Flow<List<Results>>

    @Delete
    suspend fun deleteMatch(result: Results)
}