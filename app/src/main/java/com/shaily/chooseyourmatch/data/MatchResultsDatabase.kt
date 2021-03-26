package com.shaily.chooseyourmatch.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.concurrent.locks.Lock

@Database(
    entities = [Results::class],
    version = 1
)
abstract class MatchResultsDatabase: RoomDatabase() {

    abstract fun getMatchResultsDao(): MatchResultsDao

    companion object {

        @Volatile
        private var instance: MatchResultsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MatchResultsDatabase::class.java,
                "matchResults_db.db"
            ).build()

    }
}