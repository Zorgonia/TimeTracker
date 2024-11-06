package com.kyang.timetracker.history.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyang.core_data.Result
import com.kyang.timetracker.history.repository.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for HistoryScreen
 * @param historyRepository HistoryRepository Repository dealing with getting history items
 */
@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyRepository: HistoryRepository
): ViewModel() {

    private val _historyUIState: MutableStateFlow<HistoryUiState> = MutableStateFlow(HistoryUiState())
    val historyUIState: StateFlow<HistoryUiState> = _historyUIState

    fun getHistoryItems() {
        viewModelScope.launch {
            _historyUIState.update {
                it.copy(isLoading = true)
            }
            when(val result = historyRepository.getTimeEntries()) {
                is Result.Success -> {
                    _historyUIState.update {
                        it.copy(historyItems = result.data, isLoading = false)
                    }
                }
                is Result.Error -> {
                    _historyUIState.update {
                        it.copy(errorMessage = result.exception.message, isLoading = false)
                    }
                }
            }

        }
    }
}