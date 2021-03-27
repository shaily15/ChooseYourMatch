package com.shaily.chooseyourmatch.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shaily.chooseyourmatch.models.Results

@Database(
    entities = [Results::class],
    version = 1)
@TypeConverters(Converters::class)
abstract class MatchResultsDatabase: RoomDatabase() {

    abstract fun getMatchResultsDao(): MatchResultsDao
}