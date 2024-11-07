package com.kyang.timetracker.history.viewmodel

import com.kyang.core_data.Result
import com.kyang.core_data.test.MainDispatcherRule
import com.kyang.timetracker.history.model.LocalTimeEntry
import com.kyang.timetracker.history.repository.HistoryRepository
import com.kyang.timetracker.history.ui.HistoryViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class HistoryViewModelTest {
    private lateinit var historyViewModel: HistoryViewModel
    private var mockedRepo = mock<HistoryRepository>()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        historyViewModel = HistoryViewModel(mockedRepo)
    }

    private val testData = listOf(
        LocalTimeEntry(
            id = 0,
            specifiedTime = "1",
            startTime = "1",
            endTime = "2",
            inRange = true
        ),
        LocalTimeEntry(
            id = 1,
            specifiedTime = "12",
            startTime = "13",
            endTime = "22",
            inRange = false
        ),
    )

    @Test
    fun `GIVEN getHistoryItems WHEN repo returns empty list THEN ui state has empty list`() = runTest {
        whenever(mockedRepo.getTimeEntries()).thenReturn(Result.Success(listOf()))
        val expected = listOf<LocalTimeEntry>()
        historyViewModel.getHistoryItems()
        assertEquals(expected, historyViewModel.historyUIState.value.historyItems)
    }

    @Test
    fun `GIVEN getHistoryItems WHEN repo returns non empty list THEN ui state has non empty list`() = runTest {
        whenever(mockedRepo.getTimeEntries()).thenReturn(Result.Success(testData))
        val expected = testData
        historyViewModel.getHistoryItems()
        assertEquals(expected, historyViewModel.historyUIState.value.historyItems)
    }
}