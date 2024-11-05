package com.kyang.timetracker.history.repository

import com.kyang.core_data.Result
import com.kyang.timetracker.history.model.LocalTimeEntry

interface HistoryRepository {

    suspend fun getTimeEntries(): Result<List<LocalTimeEntry>>

}