package com.kyang.timetracker.home.ui

/**
 * The Home screen UI state
 * @param specifiedTime String The specified time
 * @param startTime String The start time
 * @param endTime String The end time
 * @param checkEnabled Boolean Whether the check button should be enabled
 * @param errorMessage String? The error message to display
 * @param timeInRange Boolean? Whether the specified time is in range
 * @param showSaveButton Boolean Whether the save button should be shown
 * @param showSavedText Boolean Whether the saved text should be shown
 */
data class HomeUiState(
    val specifiedTime: String = "",
    val startTime: String = "",
    val endTime: String = "",
    val checkEnabled: Boolean = false,
    val errorMessage: String? = null,
    val timeInRange: Boolean? = null,
    val showSaveButton: Boolean = false,
    val showSavedText: Boolean = false,
)
