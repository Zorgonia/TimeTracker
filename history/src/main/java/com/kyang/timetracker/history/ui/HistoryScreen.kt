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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kyang.timetracker.history.R
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
        items(uiState.historyItems, key = {it.id}) { item ->
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

@Composable
fun HeaderRow(modifier: Modifier = Modifier) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(stringResource(R.string.history_start_time_header), modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        Text(stringResource(R.string.history_end_time_header), modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        Text(stringResource(R.string.history_specified_time_header), modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        Text(stringResource(R.string.history_in_range_header).toString(), modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
    }
}


@Composable
fun HistoryRow(
    modifier: Modifier = Modifier,
    startTime: String,
    endTime: String,
    specifiedTime: String,
    inRange: Boolean
) {
    Row(
        modifier.background(
            if (inRange) {
                Color.Green.copy(alpha = 0.5f)
            } else {
                Color.Red.copy(alpha = 0.5f)
            }
        )
    ) {
        Text(startTime, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        Text(endTime, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        Text(specifiedTime, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        Text(inRange.toString(), modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
    }
}