package com.kyang.timetracker.home.repository

import com.kyang.core_data.Result

interface HomeRepository {

    /**
     * Check if specified time is in range of start and end time
     * @param specifiedTime The specified time
     * @param startTime The start time
     * @param endTime The end time
     * @return On success: whether the specified time is in range of start and end time, on failure: an error
     */
    suspend fun specifiedTimeInRange(
        specifiedTime: String,
        startTime: String,
        endTime: String
    ): Result<Boolean>

    /**
     * Save time entry to specified storage type
     * @param specifiedTime The specified time
     * @param startTime The start time
     * @param endTime The end time
     * @param inRange Whether the specified time is in range of start and end time
     * @return On success: whether the storage was successful, on failure: an exception
     */
    suspend fun saveTimeEntry(
        startTime: String,
        endTime: String,
        specifiedTime: String,
        inRange: Boolean
    ): Result<Boolean>
}