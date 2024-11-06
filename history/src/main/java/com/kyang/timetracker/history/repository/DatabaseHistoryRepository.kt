package com.kyang.timetracker.history.repository

import com.kyang.core_data.Result
import com.kyang.core_data.database.TimeDao
import com.kyang.timetracker.history.model.LocalTimeEntry
import com.kyang.timetracker.history.model.mapper.toLocalTimeEntry
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of HistoryRepository with time database
 */
@Singleton
class DatabaseHistoryRepository @Inject constructor(
    private val timeDao: TimeDao
): HistoryRepository {

    override suspend fun getTimeEntries(): Result<List<LocalTimeEntry>> {
        try {
            val entries = timeDao.getAll()
            return Result.Success(entries.map { it.toLocalTimeEntry() })
        } catch (e: Exception) {
            return Result.Error(e)
        }
    }
}