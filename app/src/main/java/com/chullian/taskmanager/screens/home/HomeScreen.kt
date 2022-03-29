package com.chullian.taskmanager.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chullian.taskmanager.components.*
import com.chullian.taskmanager.models.TaskGroup
import kotlinx.coroutines.launch

/**
 * Created by binMammootty on 22/03/2022.
 */

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel,
    scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState()
) {
    // UiState of the HomeScreen
    val uiState by homeViewModel.uiState.collectAsState()

    HomeRoute(
        uiState = uiState,
        addNewGroup = { homeViewModel.addNewGroup() },
        onGroupNameChanged = { homeViewModel.newGroupName(it) },
        onGroupColorChanged = { homeViewModel.newGroupColor(it) },
        onSaveGroup = { homeViewModel.saveGroup() },
        onRemoveGroup = { homeViewModel.removeGroup(it) },
        onErrorDismiss = { },
        onInteractWithGroup = { },
        scaffoldState = scaffoldState,
    )
}


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class, androidx.compose.ui.ExperimentalComposeUiApi::class)
@Composable
fun HomeRoute(
    uiState: HomeUiState,
    addNewGroup: () -> Unit = {},
    onGroupNameChanged: (String) -> Unit = {},
    onGroupColorChanged: (Color) -> Unit = {},
    onSaveGroup: () -> Unit = {},
    onRemoveGroup: (TaskGroup) -> Unit = {},
    onErrorDismiss: () -> Unit = {},
    onInteractWithGroup: () -> Unit = {},
    scaffoldState: BottomSheetScaffoldState
) {
    val scope = rememberCoroutineScope()
    var swipeToDismissState :DismissState  = rememberDismissState()
    val keyboardController = LocalSoftwareKeyboardController.current
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar("Tasks") },
        content = {
            when (uiState) {
                is HomeUiState.HasGroup -> {
                    LazyColumn(modifier = Modifier.padding(5.dp), content = {
                        items(uiState.groups) { group ->
                            SwipeToDismiss(
                                state = swipeToDismissState,
                                background = {
                                Box(modifier = Modifier.background(Color.Red))
                            }) {
                                GroupCard(taskGroup = group)
                            }
                            when(swipeToDismissState.currentValue){
                                DismissValue.DismissedToStart -> {
//                                    swipeToDismissState = DismissState(DismissValue.Default)
                                    onRemoveGroup(group)
                                }
                                DismissValue.Default->{
                                    print("DEFAULT")
                                }
                            }

                        }
                        item {
                            GroupCardNew("Add Group") {
                                addNewGroup()
                            }
                        }
                    })
                }
                is HomeUiState.NoGroups -> {
                    Column(modifier = Modifier.padding(5.dp)) {
                        GroupCardNew("Add Group") {
                            addNewGroup()
                        }
                    }
                }
            }
            when (uiState.openGroupEditor) {
                true -> scope.launch {
                    scaffoldState.bottomSheetState.expand()
                }
                false -> {
                    scope.launch {
                        scaffoldState.bottomSheetState.collapse()
                    }
                }
            }
        },
        sheetContent = {
            BottomsheetCard {
                Column {
                    TaskManagerTextField(
                        value = uiState.newGroupName,
                        label = "Enter group Name",
                        imeAction = ImeAction.Done,
                        onValueChanged = { onGroupNameChanged(it) },
                        onImeAction = {
                            onSaveGroup()
                            keyboardController?.hide()
                        })
                    LazyRow(contentPadding = PaddingValues(5.dp), content = {
                        items(uiState.colors) { color: Color ->
                            GroupColorItem(
                                color = color,
                                currentColor = uiState.newGroupColor,
                                onClickedColor = { onGroupColorChanged(it) })
                        }
                    })
                }

            }
        },
        sheetPeekHeight = 0.dp
    )

}

@Preview(showSystemUi = true)
@Composable
fun HomePreview() {
//    HomeRoute(HomeUiState())

}