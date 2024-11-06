package com.kyang.timetracker.history.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.kyang.timetracker.history.R

/**
 * Header Row component, to display what each column represents
 */
@Composable
internal fun HeaderRow(modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        Text(
            stringResource(R.string.history_start_time_header),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            stringResource(R.string.history_end_time_header),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            stringResource(R.string.history_specified_time_header),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            stringResource(R.string.history_in_range_header).toString(),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
    }
}

/**
 * History Row component, to display each time entry
 * @param startTime String The start time of the time entry
 * @param endTime String The end time of the time entry
 * @param specifiedTime String The specified time of the time entry
 * @param inRange Boolean Whether the specified time is in the time range
 */
@Composable
internal fun HistoryRow(
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

@Preview
@Composable
private fun HeaderRowPreview() {
    HeaderRow()
}

@Preview
@Composable
private fun HistoryRowPreview() {
    HistoryRow(
        startTime = "12",
        endTime = "13",
        specifiedTime = "1",
        inRange = false
    )
}