package com.kyang.timetracker.home.repository

import javax.inject.Inject
import com.kyang.core_data.Result
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor() : HomeRepository {
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
}