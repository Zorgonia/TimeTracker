package com.kyang.timetracker.history.helper

import com.kyang.core_data.database.TimeEntry
import com.kyang.timetracker.history.model.LocalTimeEntry
import com.kyang.timetracker.history.model.mapper.toLocalTimeEntry
import junit.framework.TestCase.assertEquals
import org.junit.Test

class TimeEntryMappersTest {
    @Test
    fun `GIVEN toLocalTimeEntry WHEN TimeEntry is valid THEN returns LocalTimeEntry`() {
        val timeEntry = TimeEntry(
            id = 1,
            specifiedTime = 10,
            startTime = 5,
            endTime = 15,
            inRange = true
        )

        val actual = timeEntry.toLocalTimeEntry()

        val expected = LocalTimeEntry(
            id = 1,
            specifiedTime = "10",
            startTime = "5",
            endTime = "15",
            inRange = true
        )
        assertEquals(expected, actual)
    }
}