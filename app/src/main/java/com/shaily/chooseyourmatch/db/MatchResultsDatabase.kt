package com.shaily.chooseyourmatch.db

import android.content.Context
import androidx.room.*
import com.shaily.chooseyourmatch.models.Results

@Database(
    entities = [Results::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class MatchResultsDatabase: RoomDatabase() {

    abstract fun getMatchResultsDao(): MatchResultsDao

//    companion object {
//
//        @Volatile
//        private var instance: MatchResultsDatabase? = null
//        private val LOCK = Any()
//
//        operator fun invoke(context: Context) = instance
//            ?: synchronized(LOCK) {
//            instance
//                ?: createDatabase(
//                    context
//                )
//                    .also { instance = it }
//        }
//
//        private fun createDatabase(context: Context) =
//            Room.databaseBuilder(
//                context.applicationContext,
//                MatchResultsDatabase::class.java,
//                "matchResults_db.db"
//            ).build()
//
//    }
}