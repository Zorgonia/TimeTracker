package com.kyang.timetracker.home.repository

import android.util.Log
import javax.inject.Inject
import com.kyang.core_data.Result
import com.kyang.core_data.database.TimeDao
import com.kyang.core_data.database.TimeEntry
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val timeDao: TimeDao
) : HomeRepository {

    override suspend fun specifiedTimeInRange(
        specifiedTime: String,
        startTime: String,
        endTime: String
    ): Result<Boolean> {
        try {
            val specifiedTimeInt = specifiedTime.toInt()
            val startTimeInt = startTime.toInt()
            val endTimeInt = endTime.toInt()

            if (startTimeInt == endTimeInt) {
                return Result.Success(specifiedTimeInt == startTimeInt)
            }
            if (startTimeInt < endTimeInt) {
                return Result.Success(specifiedTimeInt in startTimeInt until endTimeInt)
            }
            // if startTime > endTime, check whether specified time between end to 23 or 0 to start time
            val isTimeInRange =
                specifiedTimeInt in endTimeInt..23 || specifiedTimeInt in 0 until startTimeInt
            return Result.Success(isTimeInRange)
        } catch (e: NumberFormatException) {
            //if one input is not a valid number, return an error
            return Result.Error(e)
        }
    }

    override suspend fun saveTimeEntry(
        startTime: String,
        endTime: String,
        specifiedTime: String,
        inRange: Boolean
    ): Result<Boolean> {
        try {
            val startConverted = startTime.toLong()
            val endConverted = endTime.toLong()
            val specifiedConverted = specifiedTime.toLong()

            val timeEntry = TimeEntry(
                startTime = startConverted,
                endTime = endConverted,
                specifiedTime = specifiedConverted,
                inRange = inRange
            )
            timeDao.insert(timeEntry)
            return Result.Success(true)
        } catch (e: Exception) {
            return Result.Error(e)
        }
    }
}