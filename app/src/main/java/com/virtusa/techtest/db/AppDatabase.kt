package com.virtusa.techtest.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [
    AlbumEntity::class
], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}