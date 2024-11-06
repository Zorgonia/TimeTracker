package com.kyang.timetracker.home.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kyang.timetracker.home.R


@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel) {
    val uiState = viewModel.homeUiState.collectAsStateWithLifecycle().value
    HomeScreen(modifier = modifier,
        uiState = uiState,
        onSpecifiedChange = {
            viewModel.updateSpecifiedTime(it)
        },
        onStartChange = {
            viewModel.updateStartTime(it)
        },
        onEndChange = {
            viewModel.updateEndTime(it)
        },
        checkSpecifiedTime = { viewModel.checkWhetherTimeInRange() },
        onSave = { viewModel.saveTimeEntry() })
}


@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onSpecifiedChange: (String) -> Unit,
    onStartChange: (String) -> Unit,
    onEndChange: (String) -> Unit,
    checkSpecifiedTime: () -> Unit,
    onSave: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputTextField(
            text = uiState.specifiedTime,
            onChange = onSpecifiedChange,
            label = R.string.specified_time,
            modifier = Modifier.padding(
                vertical = 12.dp
            )
        )
        InputTextField(
            text = uiState.startTime,
            onChange = onStartChange,
            label = R.string.start_time,
            modifier = Modifier.padding(
                vertical = 12.dp
            )
        )
        InputTextField(
            text = uiState.endTime,
            onChange = onEndChange,
            label = R.string.end_time,
            modifier = Modifier.padding(
                vertical = 12.dp
            )
        )

        Button(onClick = checkSpecifiedTime, enabled = uiState.checkEnabled) {
            Text(stringResource(R.string.check_time))
        }

        if (uiState.showSaveButton) {
            Button(onClick = onSave) {
                Text(stringResource(R.string.save_results_button))
            }
        }

        uiState.timeInRange?.let { inRange ->
            Text(
                stringResource(
                    if (inRange) R.string.time_in_range else R.string.time_not_in_range
                )
            )
        }

        if (uiState.showSavedText) {
            Text(stringResource(R.string.results_saved))
        }

        uiState.errorMessage?.let { message ->
            Text(message)
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(uiState = HomeUiState(),
        onStartChange = {},
        onEndChange = {},
        onSpecifiedChange = {},
        checkSpecifiedTime = {},
        onSave = {})
}