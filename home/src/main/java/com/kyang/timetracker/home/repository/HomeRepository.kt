package com.kyang.timetracker.home.repository

import com.kyang.core_data.Result

interface HomeRepository {

    /**
     * Check if specified time is in range of start and end time
     * @param specifiedTime String The specified time
     * @param startTime String The start time
     * @param endTime String The end time
     * @return Result<Boolean> Whether the specified time is in range of start and end time, or an error
     */
    suspend fun specifiedTimeInRange(
        specifiedTime: String,
        startTime: String,
        endTime: String
    ): Result<Boolean>

    /**
     * Save time entry to specified storage type
     * @param specifiedTime String The specified time
     * @param startTime String The start time
     * @param endTime String The end time
     * @param inRange Boolean Whether the specified time is in range of start and end time
     * @return Result<Boolean> Whether the storage was successful, or an error
     */
    suspend fun saveTimeEntry(
        startTime: String,
        endTime: String,
        specifiedTime: String,
        inRange: Boolean
    ): Result<Boolean>
}