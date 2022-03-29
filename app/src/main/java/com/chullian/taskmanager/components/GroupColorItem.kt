package com.chullian.taskmanager.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Created by binMammootty on 22/03/2022.
 */

@Composable
fun GroupColorItem(
    color: Color = Color.Transparent,
    currentColor: Color = Color.Transparent,
    onClickedColor: (Color) -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(5.dp)
            .height(30.dp)
            .width(30.dp)
            .clip(CircleShape)
            .clickable {
                onClickedColor(color)
            }
            .background(color),
    ) {
        if (currentColor == color)
            Box(
                modifier = Modifier
                    .height(20.dp)
                    .width(20.dp)
                    .clip(CircleShape)
                    .background(Color.White),
            ) {

            }
    }
}