package com.kyang.timetracker.history.model.mapper

import com.kyang.core_data.database.TimeEntry
import com.kyang.timetracker.history.model.LocalTimeEntry


/**
 * Converts a [TimeEntry] to a [LocalTimeEntry]
 *
 * @return LocalTimeEntry - The converted LocalTimeEntry
 */
fun TimeEntry.toLocalTimeEntry(): LocalTimeEntry {
    return LocalTimeEntry(
        id = id,
        startTime = startTime.toString(),
        endTime = endTime.toString(),
        specifiedTime = specifiedTime.toString(),
        inRange = inRange
    )
}