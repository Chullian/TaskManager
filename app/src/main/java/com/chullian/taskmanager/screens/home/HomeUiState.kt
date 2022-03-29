package com.chullian.taskmanager.screens.home

import androidx.compose.material.DismissState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.graphics.Color
import com.chullian.taskmanager.models.TaskGroup
import com.chullian.taskmanager.utils.ErrorMessage

sealed interface HomeUiState {

    val isLoading: Boolean
    val errorMessages: List<ErrorMessage>
    val searchInput: String
    val openGroupEditor: Boolean
    val newGroupName: String
    val newGroupColor:Color
    val colors:List<Color>

    data class NoGroups @OptIn(ExperimentalMaterialApi::class) constructor(
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>,
        override val searchInput: String,
        override val openGroupEditor: Boolean,
        override val newGroupName: String,
        override val newGroupColor: Color,
        override val colors: List<Color>
    ) : HomeUiState {


    }

    data class HasGroup @OptIn(ExperimentalMaterialApi::class) constructor(
        val groups:List<TaskGroup>,
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>,
        override val searchInput: String,
        override val openGroupEditor: Boolean,
        override val newGroupName: String,
        override val newGroupColor: Color,
        override val colors: List<Color>
    ) : HomeUiState

}
