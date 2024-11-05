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
        specified: String,
        start: String,
        end: String
    ): Result<Boolean> {
        try {
            val specifiedTime = specified.toInt()
            val startTime = start.toInt()
            val endTime = end.toInt()

            if (startTime == endTime) {
                return Result.Success(specifiedTime == startTime)
            }
            if (startTime <= endTime) {
                return Result.Success(specifiedTime in startTime until endTime)
            }
            val isTimeInRange = specifiedTime in endTime..23 || specifiedTime in 0 until startTime
            return Result.Success(isTimeInRange)
        } catch (e: NumberFormatException) {
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
            Log.d("DatabaseHistoryRepository", "getTimeEntries: $timeEntry")

            return Result.Success(true)
        } catch (e: Exception) {
            return Result.Error(e)
        }
    }
}