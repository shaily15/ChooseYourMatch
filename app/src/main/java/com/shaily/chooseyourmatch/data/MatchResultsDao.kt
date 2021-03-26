//package com.shaily.chooseyourmatch.data
//
//import androidx.room.*
//import kotlinx.coroutines.flow.Flow
//import retrofit2.Response
//
//@Dao
//interface MatchResultsDao {
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertSelectedMatch(result: Results)
//
//    @Query("SELECT * FROM matchResults")
//    fun getAllMatches(): Flow<List<Results>>
//
//    @Delete
//    suspend fun deleteMatch(result: Results)
//}