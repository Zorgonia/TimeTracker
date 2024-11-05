package com.kyang.core_data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "times")
data class TimeEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val startTime: Long,
    val endTime: Long,
    val specifiedTime: Long,
    val inRange: Boolean
)