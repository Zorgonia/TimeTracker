package com.kyang.core_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Functions to interact with time database
 */
@Dao
interface TimeDao {
    @Query("SELECT * FROM times")
    suspend fun getAll(): List<TimeEntry>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(timeEntry: TimeEntry)
}