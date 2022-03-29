package com.chullian.taskmanager.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

/**
 * Created by binMammootty on 22/03/2022.
 */

@Composable
fun TopBar(
    title: String = "",
    navigationIcon: @Composable (() -> Unit)? = null,
) {
    TopAppBar(
        title = {
            Text(text = title, modifier = Modifier.fillMaxWidth(), style = TextStyle(textAlign = TextAlign.Center))
        },
        navigationIcon = navigationIcon,
    )

}

@Preview
@Composable
fun TopBarPreview() {
    TopBar("Tasks")
}