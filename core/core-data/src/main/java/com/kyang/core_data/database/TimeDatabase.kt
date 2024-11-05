package com.kyang.core_data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TimeEntry::class], version = 1)
abstract class TimeDatabase : RoomDatabase() {
    abstract fun timeDao(): TimeDao
}