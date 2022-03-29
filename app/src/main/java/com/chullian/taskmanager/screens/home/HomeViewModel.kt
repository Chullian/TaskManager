package com.chullian.taskmanager.screens.home

import androidx.compose.material.DismissState
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chullian.taskmanager.models.TaskGroup
import com.chullian.taskmanager.ui.theme.groupColors
import com.chullian.taskmanager.utils.ErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * Created by binMammootty on 22/03/2022.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {


    private val viewModelState = MutableStateFlow(HomeViewModelState(isLoading = true))
    private val list = mutableListOf<TaskGroup>()

    // UI state exposed to the UI
    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    fun addNewGroup() {
        viewModelState.update { currentUiState ->
            currentUiState.copy(openGroupEditor = !currentUiState.openGroupEditor)
        }
    }

    fun newGroupName(value: String) {
        viewModelState.update { currentUiState ->
            currentUiState.copy(newGroupName = value)
        }
    }

    fun newGroupColor(value: Color) {
        viewModelState.update { currentUiState ->
            currentUiState.copy(newGroupColor = value)
        }
    }

    fun saveGroup() {
        val color = viewModelState.value.newGroupColor
        val name = viewModelState.value.newGroupName
        list.add(
            TaskGroup(
                id = 0,
                name = name,
                color = color,
                numberOfTasks = 10
            )
        )
        viewModelState.update { currentUiState ->
            currentUiState.copy(
                groups = list,
                newGroupColor = currentUiState.colors[0],
                newGroupName = "",
                openGroupEditor = !currentUiState.openGroupEditor
            )
        }
    }

    fun removeGroup(taskGroup: TaskGroup) {
        val items = list.filter { it != taskGroup }
        list.clear()
        list.addAll(items)
        viewModelState.update { currentUiState ->
            currentUiState.copy(
                groups = list,
                newGroupColor = currentUiState.colors[0],
                newGroupName = "",
                openGroupEditor = false,
            )
        }
    }
}

private data class HomeViewModelState(
    val groups: List<TaskGroup>? = null,
    val colors: List<Color> = groupColors,
    val isLoading: Boolean = false,
    val openGroupEditor: Boolean = false,
    val addNewGroup: Boolean = false,
    val errorMessages: List<ErrorMessage> = emptyList(),
    val searchInput: String = "",
    val newGroupName: String = "",
    val newGroupColor: Color = colors[0]
) {

    @OptIn(ExperimentalMaterialApi::class)
    fun toUiState(): HomeUiState =
        if (groups == null) {
            HomeUiState.NoGroups(
                isLoading = isLoading,
                errorMessages = errorMessages,
                searchInput = searchInput,
                openGroupEditor = openGroupEditor,
                newGroupName = newGroupName,
                newGroupColor = newGroupColor,
                colors = colors
            )
        } else {
            HomeUiState.HasGroup(
                groups = groups,
                isLoading = isLoading,
                errorMessages = errorMessages,
                searchInput = searchInput,
                openGroupEditor = openGroupEditor,
                newGroupName = newGroupName,
                newGroupColor = newGroupColor,
                colors = colors,
            )
        }
}