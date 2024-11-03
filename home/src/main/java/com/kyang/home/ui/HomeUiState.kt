package com.kyang.home.ui

data class HomeUiState(
    val specifiedTime: String = "",
    val startTime: String = "",
    val endTime: String = "",
    val timeInRange: Boolean? = null
)
