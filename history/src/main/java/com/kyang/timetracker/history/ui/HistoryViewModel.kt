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

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyRepository: HistoryRepository
): ViewModel() {

    private val _historyUIState: MutableStateFlow<HistoryUiState> = MutableStateFlow(HistoryUiState())
    val historyUIState: StateFlow<HistoryUiState> = _historyUIState

    fun getHistoryItems() {
        viewModelScope.launch {
            val result = historyRepository.getTimeEntries()
            when(result) {
                is Result.Success -> {
                    _historyUIState.update {
                        it.copy(historyItems = result.data)
                    }
                }
                is Result.Error -> {
                    _historyUIState.update {
                        it.copy(errorMessage = result.exception.message)
                    }
                }
            }

        }
    }
}