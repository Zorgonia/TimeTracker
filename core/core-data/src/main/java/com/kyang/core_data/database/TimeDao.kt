package com.kyang.core_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TimeDao {
    @Query("SELECT * FROM times")
    fun getAllFlow(): Flow<List<TimeEntry>>

    @Query("SELECT * FROM times")
    fun getAll(): List<TimeEntry>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(timeEntry: TimeEntry)
}