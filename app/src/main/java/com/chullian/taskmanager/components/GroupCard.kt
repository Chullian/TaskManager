package com.chullian.taskmanager.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chullian.taskmanager.models.TaskGroup
import com.chullian.taskmanager.ui.theme.Shapes

/**
 * Created by binMammootty on 22/03/2022.
 */


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GroupCardNew(
    text:String="",
    onClicked:()->Unit = {}
) {
    Card(modifier = Modifier
        .padding(2.dp)
        .fillMaxWidth(), onClick = onClicked, shape = Shapes.small, content = {
        Column (modifier=Modifier.padding(15.dp)){
            Text(text = text)
        }
    })
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GroupCard(
    taskGroup: TaskGroup,
    onClicked:()->Unit = {}
) {
    Card(modifier = Modifier
        .padding(2.dp)
        .fillMaxWidth(), onClick = onClicked, shape = Shapes.small, content = {
        Row (verticalAlignment = Alignment.CenterVertically, modifier=Modifier.padding(15.dp)){
            GroupColorItem(taskGroup.color)
            Spacer(modifier = Modifier.width(5.dp))
            Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = taskGroup.name)
                Text(text = taskGroup.numberOfTasks.toString())
            }
        }
    })
}

@Preview
@Composable
fun GroupCardNewPreview() {
    GroupCard(TaskGroup(name="Add Group" ,color=Color.Red,id=0, numberOfTasks = 10))
}