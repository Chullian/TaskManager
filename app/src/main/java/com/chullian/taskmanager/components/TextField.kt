package com.chullian.taskmanager.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Created by binMammootty on 22/03/2022.
 */

@Composable
fun TaskManagerTextField(
    value: String = "heyyyy",
    label: String = "",
    imeAction: ImeAction = ImeAction.Default,
    onValueChanged: (String) -> Unit = {},
    onImeAction: () -> Unit = {},

    ) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(10.dp),
        value = value,
        onValueChange = { onValueChanged(it) },
        placeholder = { Text(text = label) },
        keyboardOptions = KeyboardOptions(imeAction = imeAction),
        keyboardActions = KeyboardActions(onAny = { onImeAction() }),
        colors = TextFieldDefaults
            .textFieldColors(
                backgroundColor = Color.Transparent,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            )
    )
}


@Preview
@Composable
fun TaskManagerTextFieldPrev() {
    TaskManagerTextField(
        value = "",
        label = "Howdii"
    )
}