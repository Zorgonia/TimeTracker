package com.kyang.timetracker.history.repo

import com.kyang.core_data.Result
import com.kyang.core_data.database.TimeDao
import com.kyang.core_data.database.TimeEntry
import com.kyang.timetracker.history.model.LocalTimeEntry
import com.kyang.timetracker.history.repository.DatabaseHistoryRepository
import com.kyang.timetracker.history.repository.HistoryRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class DatabaseHistoryRepositoryTest {
    private lateinit var historyRepository: HistoryRepository
    private var mockedDao = mock<TimeDao>()

    @Before
    fun setup() {
        historyRepository = DatabaseHistoryRepository(mockedDao)
    }

    private val testData = listOf(
        TimeEntry(
            id = 0,
            specifiedTime = 1,
            startTime = 1,
            endTime = 2,
            inRange = true
        )
    )

    @Test
    fun `GIVEN getTimeEntries WHEN db returns empty list THEN returns empty list`() = runTest {
        whenever(mockedDao.getAll()).thenReturn(listOf())
        val actual = historyRepository.getTimeEntries()
        assertEquals(Result.Success(listOf<LocalTimeEntry>()), actual)
    }

    @Test
    fun `GIVEN getTimeEntries WHEN db returns non empty list THEN returns non empty local list`() = runTest {
        whenever(mockedDao.getAll()).thenReturn(testData)
        val expected = listOf(LocalTimeEntry(
            id = 0,
            specifiedTime = "1",
            startTime = "1",
            endTime = "2",
            inRange = true
        ))
        val actual = historyRepository.getTimeEntries()
        assertEquals(Result.Success(expected), actual)
    }
}