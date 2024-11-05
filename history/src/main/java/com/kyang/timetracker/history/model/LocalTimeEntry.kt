package com.kyang.timetracker.history.model

data class LocalTimeEntry(
    val id: Int,
    val startTime: String,
    val endTime: String,
    val specifiedTime: String,
    val inRange: Boolean
)
