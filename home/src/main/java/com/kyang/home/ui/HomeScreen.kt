package com.kyang.home.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kyang.home.R


@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel) {
    val uiState = viewModel.homeUiState.collectAsStateWithLifecycle().value
    HomeScreen(uiState = uiState, {}, {}, {})
}

@Composable
internal fun HomeScreen(
    uiState: HomeUiState,
    onSpecifiedChange: (String) -> Unit,
    onStartChange: (String) -> Unit,
    onEndChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputTextField(
            text = uiState.specifiedTime,
            onChange = onSpecifiedChange,
            label = R.string.specified_time
        )
        InputTextField(
            text = uiState.startTime, onChange = onStartChange, label = R.string.start_time
        )
        InputTextField(
            text = uiState.endTime, onChange = onEndChange, label = R.string.end_time
        )

        Button(onClick = {}) {
            Text(stringResource(R.string.check_time))
        }

        uiState.timeInRange?.let { inRange ->
            Text(
                stringResource(
                    if (inRange) R.string.time_in_range else R.string.time_not_in_range
                )
            )
        }
    }
}

@Composable
fun InputTextField(
    text: String,
    onChange: (String) -> Unit,
    @StringRes label: Int,
) {
    TextField(text, onChange, label = {
        Text(stringResource(label))
    }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
}