package com.kyang.core_data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data Module for storage of time entry
 * @param id Int Primary key
 * @param startTime Long Start time of entry
 * @param endTime Long End time of entry
 * @param specifiedTime Long Specified time of entry
 * @param inRange Boolean Whether the specified time is in the time range
 */
@Entity(tableName = "times")
data class TimeEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val startTime: Long,
    val endTime: Long,
    val specifiedTime: Long,
    val inRange: Boolean
)