package com.virtusa.techtest.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlbumDao {

    @Insert
    fun insertAll(accounts: List<AlbumEntity>)

    @Query("SELECT * FROM Album")
    fun getAlbums(): List<AlbumEntity>

    @Query("SELECT COUNT(*) FROM Album")
    fun count(): Int
}