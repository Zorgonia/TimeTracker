package com.kyang.timetracker.history.ui

import com.kyang.timetracker.history.model.LocalTimeEntry

data class HistoryUiState(
    val historyItems: List<LocalTimeEntry> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
