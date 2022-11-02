package com.kamilkulka.randletics.di

import android.content.Context
import androidx.room.Room
import com.kamilkulka.randletics.data.WorkoutDatabase
import com.kamilkulka.randletics.data.WorkoutDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideWorkoutDao(workoutDatabase: WorkoutDatabase): WorkoutDatabaseDao =
        workoutDatabase.workoutsDao()

    @Singleton
    @Provides
    fun provideWorkoutDatabase(@ApplicationContext context: Context): WorkoutDatabase =
        Room.databaseBuilder(context,WorkoutDatabase::class.java,"workouts_db")
            .fallbackToDestructiveMigration().createFromAsset("database/workouts_db.db").build()
}