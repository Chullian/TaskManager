package com.chullian.taskmanager.models

import androidx.compose.ui.graphics.Color


/**
 * Created by binMammootty on 22/03/2022.
 */
data class TaskGroup(
    val id:Long,
    val name:String,
    val color: Color,
    val numberOfTasks:Long,
)
