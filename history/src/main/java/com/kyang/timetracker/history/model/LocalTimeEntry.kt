package com.kyang.timetracker.history.model

/**
 * Data class for local time entry
 *
 * @param id Int The id of the time entry
 * @param startTime StringThe start time of the time entry
 * @param endTime String The end time of the time entry
 * @param specifiedTime String The specified time of the time entry
 * @param inRange Boolean Whether the specified time is in the time range
 */
data class LocalTimeEntry(
    val id: Int,
    val startTime: String,
    val endTime: String,
    val specifiedTime: String,
    val inRange: Boolean
)
