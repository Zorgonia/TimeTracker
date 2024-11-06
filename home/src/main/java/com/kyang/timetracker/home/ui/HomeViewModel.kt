package com.kyang.timetracker.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyang.core_data.Result
import com.kyang.timetracker.home.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for HistoryScreen
 * @param homeRepository HomeRepository Repository dealing with home data functions
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState

    fun verifyTimeText(text: String): Boolean {
        //prevent user from typing two or more zeroes
        if (text.contains("00")) return false
        val intTime = text.toIntOrNull() ?: return false
        return intTime in 0..23
    }

    fun updateStartTime(text: String) {
        if (verifyTimeText(text) || text.isEmpty()) {
            //if start time is updated successfully, hide the save button/previous result, if any
            _homeUiState.update {
                it.copy(
                    startTime = text,
                    checkEnabled = it.endTime.isNotEmpty() && it.specifiedTime.isNotEmpty() && text.isNotEmpty(),
                    errorMessage = null,
                    showSaveButton = false,
                    showSavedText = false,
                    timeInRange = null
                )
            }
        }
    }

    fun updateEndTime(text: String) {
        if (verifyTimeText(text) || text.isEmpty()) {
            //if end time is updated successfully, hide the save button/previous result, if any
            _homeUiState.update {
                it.copy(
                    endTime = text,
                    checkEnabled = it.startTime.isNotEmpty() && it.specifiedTime.isNotEmpty() && text.isNotEmpty(),
                    errorMessage = null,
                    showSaveButton = false,
                    showSavedText = false,
                    timeInRange = null
                )
            }
        }
    }

    fun updateSpecifiedTime(text: String) {
        if (verifyTimeText(text) || text.isEmpty()) {
            //if specified time is updated successfully, hide the save button/previous result, if any
            _homeUiState.update {
                it.copy(
                    specifiedTime = text,
                    checkEnabled = it.endTime.isNotEmpty() && it.startTime.isNotEmpty() && text.isNotEmpty(),
                    errorMessage = null,
                    showSaveButton = false,
                    showSavedText = false,
                    timeInRange = null
                )
            }
        }
    }

    fun checkWhetherTimeInRange() {
        viewModelScope.launch {
            val result = homeRepository.specifiedTimeInRange(
                specifiedTime = _homeUiState.value.specifiedTime,
                endTime = _homeUiState.value.endTime,
                startTime = _homeUiState.value.startTime
            )
            when (result) {
                is Result.Success -> {
                    _homeUiState.update {
                        it.copy(
                            timeInRange = result.data,
                            showSaveButton = true
                        )
                    }
                }
                is Result.Error -> {
                    _homeUiState.update {
                        it.copy(
                            errorMessage = result.exception.message
                        )
                    }
                }
            }
        }
    }

    fun saveTimeEntry() {
        viewModelScope.launch {
            _homeUiState.value.let { uiState ->
                val result = homeRepository.saveTimeEntry(
                    startTime = uiState.startTime,
                    endTime = uiState.endTime,
                    specifiedTime = uiState.specifiedTime,
                    inRange = uiState.timeInRange ?: false
                )

                when (result) {
                    is Result.Success -> {
                        _homeUiState.update {
                            it.copy(
                                showSavedText = true
                            )
                        }
                    }
                    is Result.Error -> {
                        _homeUiState.update {
                            it.copy(
                                errorMessage = result.exception.message
                            )
                        }
                    }
                }
            }
        }
    }
}