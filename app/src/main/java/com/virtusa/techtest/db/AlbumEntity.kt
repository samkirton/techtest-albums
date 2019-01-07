package com.virtusa.techtest.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Album")
data class AlbumEntity(
    @ColumnInfo(name = "name") val name: String,
    @PrimaryKey(autoGenerate = true) val uid: Int = 0
)
