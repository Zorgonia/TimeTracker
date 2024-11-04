package com.kyang.timetracker.home.viewmodel

import com.kyang.timetracker.home.repository.HomeRepository
import com.kyang.timetracker.home.repository.HomeRepositoryImpl
import com.kyang.timetracker.home.ui.HomeViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel(mock<HomeRepository>())
    }

    @Test
    fun `GIVEN verifyTimeText WHEN time is lower bound THEN returns true`() {
        val actual = viewModel.verifyTimeText("0")
        assertEquals(true, actual)
    }

    @Test
    fun `GIVEN verifyTimeText WHEN time is higher bound THEN returns true`() {
        val actual = viewModel.verifyTimeText("23")
        assertEquals(true, actual)
    }

    @Test
    fun `GIVEN verifyTimeText WHEN out of bounds THEN returns false`() {
        val actual = viewModel.verifyTimeText("24")
        assertEquals(false, actual)
    }

    @Test
    fun `GIVEN verifyTimeText WHEN time is multiple zeroes bound THEN returns false`() {
        val actual = viewModel.verifyTimeText("000")
        assertEquals(false, actual)
    }

    @Test
    fun `GIVEN verifyTimeText WHEN alphabetic input THEN returns false`() {
        val actual = viewModel.verifyTimeText("abc")
        assertEquals(false, actual)
    }

    @Test
    fun `GIVEN updateStartTime WHEN time change is valid THEN uiState enabled is false`() {
        viewModel.updateStartTime("1")
        assertEquals(false, viewModel.homeUiState.value.checkEnabled)
    }

    @Test
    fun `GIVEN updateStartTime WHEN start end and specified time filled THEN uiState enabled is true`() {
        viewModel.updateEndTime("1")
        viewModel.updateSpecifiedTime("1")
        viewModel.updateStartTime("1")
        assertEquals(true, viewModel.homeUiState.value.checkEnabled)
    }

    @Test
    fun `GIVEN updateEndTime WHEN time change is valid THEN uiState enabled is false`() {
        viewModel.updateEndTime("1")
        assertEquals(false, viewModel.homeUiState.value.checkEnabled)
    }

    @Test
    fun `GIVEN updateEndTime WHEN start end and specified time filled THEN uiState enabled is true`() {
        viewModel.updateStartTime("1")
        viewModel.updateSpecifiedTime("1")
        viewModel.updateEndTime("1")
        assertEquals(true, viewModel.homeUiState.value.checkEnabled)
    }

    @Test
    fun `GIVEN updateSpecifiedTime WHEN time change is valid THEN uiState enabled is false`() {
        viewModel.updateSpecifiedTime("1")
        assertEquals(false, viewModel.homeUiState.value.checkEnabled)
    }

    @Test
    fun `GIVEN updateSpecifiedTime WHEN start end and specified time filled THEN uiState enabled is true`() {
        viewModel.updateStartTime("1")
        viewModel.updateEndTime("1")
        viewModel.updateSpecifiedTime("1")
        assertEquals(true, viewModel.homeUiState.value.checkEnabled)
    }
}