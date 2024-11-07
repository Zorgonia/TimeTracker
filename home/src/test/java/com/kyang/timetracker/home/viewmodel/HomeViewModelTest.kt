package com.kyang.timetracker.home.viewmodel

import com.kyang.core_data.Result
import com.kyang.core_data.test.MainDispatcherRule
import com.kyang.timetracker.home.repository.HomeRepository
import com.kyang.timetracker.home.ui.HomeViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private val mockRepository = mock<HomeRepository>()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        viewModel = HomeViewModel(mockRepository)
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

    @Test
    fun `GIVEN updateSpecifiedTime WHEN specified time filled THEN showSavedText false`() {
        viewModel.updateSpecifiedTime("1")
        assertEquals(false, viewModel.homeUiState.value.showSavedText)
    }

    @Test
    fun `GIVEN checkWhetherTimeInRange WHEN check successful THEN show time in range and save button`() = runTest {
        viewModel.updateStartTime("1")
        viewModel.updateEndTime("1")
        viewModel.updateSpecifiedTime("1")
        whenever(
            mockRepository.specifiedTimeInRange(
                "1",
                "1",
                "1"
            )
        ).thenReturn(Result.Success(true))
        viewModel.checkWhetherTimeInRange()
        assertEquals(true, viewModel.homeUiState.value.showSaveButton)
        assertEquals(true, viewModel.homeUiState.value.timeInRange)
    }

    @Test
    fun `GIVEN checkWhetherTimeInRange WHEN repo check failure THEN error message shown`() = runTest {
        whenever(
            mockRepository.specifiedTimeInRange(
                "",
                "",
                ""
            )
        ).thenReturn(Result.Error(Exception("error")))
        viewModel.checkWhetherTimeInRange()
        assertEquals("error", viewModel.homeUiState.value.errorMessage)
    }

    @Test
    fun `GIVEN saveTimeEntry WHEN save successful THEN show save dialog`() = runTest {
        //setup
        whenever(
            mockRepository.specifiedTimeInRange(
                "",
                "",
                ""
            )
        ).thenReturn(Result.Success(true))
        viewModel.checkWhetherTimeInRange()
        whenever(
            mockRepository.saveTimeEntry(
                "",
                "",
                "",
                true
            )
        ).thenReturn(Result.Success(true))
        //test
        viewModel.saveTimeEntry()
        assertEquals(true, viewModel.homeUiState.value.showSavedText)
    }

    @Test
    fun `GIVEN saveTimeEntry WHEN save failure THEN show error message`() = runTest {
        whenever(
            mockRepository.saveTimeEntry(
                "",
                "",
                "",
                false
            )
        ).thenReturn(Result.Error(Exception("error")))
        viewModel.saveTimeEntry()
        assertEquals("error", viewModel.homeUiState.value.errorMessage)
        assertEquals(false, viewModel.homeUiState.value.showSavedText)
    }
}