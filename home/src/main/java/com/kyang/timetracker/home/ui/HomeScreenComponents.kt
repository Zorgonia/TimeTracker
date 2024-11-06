package com.kyang.timetracker.home.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.kyang.timetracker.home.R

/**
 * Input text field for the home screen
 * @param text String the string
 * @param onChange (String) -> Unit lambda on text change
 * @param label Int String resource for label of text field
 */
@Composable
fun InputTextField(
    text: String, onChange: (String) -> Unit, @StringRes label: Int, modifier: Modifier = Modifier
) {
    TextField(
        value = text,
        onValueChange = onChange,
        label = {
            Text(stringResource(label))
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
    )
}

@Preview
@Composable
private fun InputTextFieldPreview() {
    InputTextField(text = "12", onChange = {}, label = R.string.specified_time)
}