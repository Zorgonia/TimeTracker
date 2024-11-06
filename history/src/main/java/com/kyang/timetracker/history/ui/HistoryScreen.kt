package com.kyang.timetracker.history.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kyang.timetracker.history.R
import com.kyang.timetracker.history.model.LocalTimeEntry
import com.kyang.timetracker.history.repository.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun HistoryScreen(modifier: Modifier = Modifier, viewModel: HistoryViewModel) {

    val uiState = viewModel.historyUIState.collectAsStateWithLifecycle().value

    HistoryScreen(modifier = modifier, uiState = uiState)
}

@Composable
private fun HistoryScreen(modifier: Modifier = Modifier, uiState: HistoryUiState) {
    LazyColumn(modifier) {
        item {
            HeaderRow(modifier.fillMaxWidth())
        }
        items(uiState.historyItems, key = { it.id }) { item ->
            HistoryRow(
                modifier = Modifier.fillMaxWidth(),
                startTime = item.startTime,
                endTime = item.endTime,
                specifiedTime = item.specifiedTime,
                inRange = item.inRange
            )
        }
    }
}

@Preview
@Composable
private fun HistoryScreenPreview() {
    HistoryScreen(
        uiState = HistoryUiState(
            historyItems = listOf(
                LocalTimeEntry(
                    id = 1,
                    startTime = "12",
                    endTime = "13",
                    specifiedTime = "1",
                    inRange = false
                )
            )
        )
    )
}