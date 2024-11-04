package com.kyang.timetracker.home.ui

/**
 * The Home screen UI state
 * @param specifiedTime String The specified time
 * @param startTime String The start time
 * @param endTime String The end time
 * @param checkEnabled Boolean Whether the check button should be enabled
 * @param errorMessage String? The error message to display
 * @param timeInRange Boolean? Whether the specified time is in range
 */
data class HomeUiState(
    val specifiedTime: String = "",
    val startTime: String = "",
    val endTime: String = "",
    val checkEnabled: Boolean = false,
    val errorMessage: String? = null,
    val timeInRange: Boolean? = null
)
