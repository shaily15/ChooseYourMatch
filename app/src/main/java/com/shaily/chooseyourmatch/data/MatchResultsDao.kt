package com.shaily.chooseyourmatch.data

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface MatchResultsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSelectedMatch(results: Results)

    @Query("SELECT * FROM matchResults")
    fun getAllMatches(): LiveData<List<Results>>

    @Delete
    suspend fun deletedMatch(results: Results)
}