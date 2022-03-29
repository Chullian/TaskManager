package com.chullian.taskmanager.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Created by binMammootty on 22/03/2022.
 */

@Composable
fun BottomsheetCard(
    modifier: Modifier = Modifier,
    content:@Composable (()->Unit)?=null
) {
    if (content != null) {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp, bottomStart = 0.dp, bottomEnd = 0.dp), content = content)
    }
}

@Preview
@Composable
fun BottomsheetCardPrev() {
    BottomsheetCard(modifier = Modifier.height(50.dp))
}