package com.virtusa.techtest.db

import android.app.Application
import androidx.room.Room
import com.virtusa.techtest.R
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {

    @JvmStatic
    @Provides
    fun appDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java,
            application.getString(R.string.app_database_name)
        ).build()
    }

    @JvmStatic
    @Provides
    fun albumDao(appDatabase: AppDatabase): AlbumDao = appDatabase.albumDao()
}