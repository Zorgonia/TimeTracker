package com.kyang.timetracker.home.repository

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import com.kyang.core_data.Result
import kotlinx.coroutines.test.runTest

class HomeRepositoryImplTest {

    private lateinit var homeRepository: HomeRepository

    @Before
    fun setup() {
        homeRepository = HomeRepositoryImpl()
    }

    @Test
    fun `GIVEN specifiedTimeInRange WHEN specified time in continuous range THEN returns true`() = runTest {
        val actual = homeRepository.specifiedTimeInRange("3", "2", "4")
        assertEquals(Result.Success(true), actual)
    }

    @Test
    fun `GIVEN specifiedTimeInRange WHEN specified time at lower bound THEN returns true`() = runTest  {
        val actual = homeRepository.specifiedTimeInRange("3", "3", "4")
        assertEquals(Result.Success(true), actual)
    }

    @Test
    fun `GIVEN specifiedTimeInRange WHEN specified time at upper bound THEN returns false`() = runTest  {
        val actual = homeRepository.specifiedTimeInRange("3", "2", "3")
        assertEquals(Result.Success(false), actual)
    }

    @Test
    fun `GIVEN specifiedTimeInRange WHEN start and end and specified time same THEN returns true`() = runTest  {
        val actual = homeRepository.specifiedTimeInRange("3", "3", "3")
        assertEquals(Result.Success(true), actual)
    }

    @Test
    fun `GIVEN specifiedTimeInRange WHEN start higher than end and specified in range THEN returns true`() = runTest  {
        val actual = homeRepository.specifiedTimeInRange("3", "22", "5")
        assertEquals(Result.Success(true), actual)
    }

    @Test
    fun `GIVEN specifiedTimeInRange WHEN inputs no valid THEN returns error of number format exception`() = runTest  {
        val actual = homeRepository.specifiedTimeInRange("3a", "2", "3")
        assertEquals(true, actual is Result.Error)
        assertEquals(NumberFormatException::class.java, (actual as Result.Error).exception.javaClass)
    }
}