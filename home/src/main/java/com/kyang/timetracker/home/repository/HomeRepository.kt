package com.kyang.timetracker.home.repository

import com.kyang.core_data.Result

interface HomeRepository {
    suspend fun specifiedTimeInRange(specified: String, start: String, end: String): Result<Boolean>
}