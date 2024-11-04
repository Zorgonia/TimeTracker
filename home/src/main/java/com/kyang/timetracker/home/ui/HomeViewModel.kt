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

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState

    fun verifyTimeText(text: String): Boolean {
        if (text.contains("00")) return false
        val intTime = text.toIntOrNull() ?: return false
        return intTime in 0..23
    }

    fun updateStartTime(text: String) {
        if (verifyTimeText(text) || text.isEmpty()) {
            _homeUiState.update {
                it.copy(
                    startTime = text,
                    checkEnabled = it.endTime.isNotEmpty() && it.specifiedTime.isNotEmpty() && text.isNotEmpty(),
                    errorMessage = null
                )
            }
        }
    }

    fun updateEndTime(text: String) {
        if (verifyTimeText(text) || text.isEmpty()) {
            _homeUiState.update {
                it.copy(
                    endTime = text,
                    checkEnabled = it.startTime.isNotEmpty() && it.specifiedTime.isNotEmpty() && text.isNotEmpty(),
                    errorMessage = null
                )
            }
        }
    }

    fun updateSpecifiedTime(text: String) {
        if (verifyTimeText(text) || text.isEmpty()) {
            _homeUiState.update {
                it.copy(
                    specifiedTime = text,
                    checkEnabled = it.endTime.isNotEmpty() && it.startTime.isNotEmpty() && text.isNotEmpty(),
                    errorMessage = null
                )
            }
        }
    }

    fun checkWhetherTimeInRange() {
        viewModelScope.launch {

            val result = homeRepository.specifiedTimeInRange(
                specified = _homeUiState.value.specifiedTime,
                end = _homeUiState.value.endTime,
                start = _homeUiState.value.startTime
            )
            when (result) {
                is Result.Success -> {
                    _homeUiState.update {
                        it.copy(
                            timeInRange = result.data
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