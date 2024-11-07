package com.kyang.timetracker.history.model

/**
 * Data class for local time entry
 *
 * @param id The id of the time entry
 * @param startTime The start time of the time entry
 * @param endTime The end time of the time entry
 * @param specifiedTime The specified time of the time entry
 * @param inRange Whether the specified time is in the time range
 */
data class LocalTimeEntry(
    val id: Int,
    val startTime: String,
    val endTime: String,
    val specifiedTime: String,
    val inRange: Boolean
)
