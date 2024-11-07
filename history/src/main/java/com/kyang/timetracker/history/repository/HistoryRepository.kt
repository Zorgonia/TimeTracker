package com.kyang.timetracker.history.repository

import com.kyang.core_data.Result
import com.kyang.timetracker.history.model.LocalTimeEntry

interface HistoryRepository {

    /**
     * Get all time entries from data source
     * @return on success: list of local time entries, on failure: exception
     */
    suspend fun getTimeEntries(): Result<List<LocalTimeEntry>>

}