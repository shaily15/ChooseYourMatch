package com.shaily.chooseyourmatch.di

import android.app.Application
import androidx.room.Room
import com.shaily.chooseyourmatch.api.MatchResultsApi
import com.shaily.chooseyourmatch.db.MatchResultsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(MatchResultsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideMatchResultsApi(retrofit: Retrofit): MatchResultsApi =
        retrofit.create(MatchResultsApi::class.java)

    @Provides
    @Singleton
    fun provideMatchDatabase(app: Application): MatchResultsDatabase =
        Room.databaseBuilder(app, MatchResultsDatabase::class.java, "matchResult_database")
            .build()
}