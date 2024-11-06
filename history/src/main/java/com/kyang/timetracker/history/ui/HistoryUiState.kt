package com.kyang.timetracker.history.ui

import com.kyang.timetracker.history.model.LocalTimeEntry

/**
 * Data class for history UI State
 * @param historyItems List<LocalTimeEntry> List of local time entries
 * @param isLoading Boolean Whether the UI is loading
 * @param errorMessage String? Error message if there is an error
 */
data class HistoryUiState(
    val historyItems: List<LocalTimeEntry> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
