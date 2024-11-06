package com.kyang.core_data.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Creation of Time Database
 */
@Database(entities = [TimeEntry::class], version = 1, exportSchema = false)
abstract class TimeDatabase : RoomDatabase() {
    abstract fun timeDao(): TimeDao
}