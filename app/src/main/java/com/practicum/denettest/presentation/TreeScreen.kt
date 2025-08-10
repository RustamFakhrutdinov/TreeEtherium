package com.practicum.denettest.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.ui.Modifier


@Composable
fun TreeScreen(viewModel: TreeViewModel) {
    val nodes = viewModel.nodes.collectAsState().value
    val currentNodeName = viewModel.currentNodeName.collectAsState().value
    val currentParentName = viewModel.currentParentName.collectAsState().value

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Current node: $currentNodeName")
        Text(text = "Children count: ${nodes.size}")

        if (currentParentName != null) {
            Button(
                onClick = { viewModel.loadChildren(currentParentName) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back")
            }
        }

        Button(
            onClick = { viewModel.addChild(currentNodeName) },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        ) {
            Text("Create Child")
        }

        if (currentParentName != null) {
            Button(
                onClick = { viewModel.deleteNode(currentNodeName ?: "") },
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            ) {
                Text("Delete Current Node")
            }
        }

        LazyColumn(modifier = Modifier.padding(top = 8.dp)) {
            items(nodes) { node ->
                Row(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
                    Text(
                        text = node.name,
                        modifier = Modifier.weight(1f).padding(end = 8.dp)
                    )
                    Button(
                        onClick = { viewModel.loadChildren(node.name) }
                    ) {
                        Text("Open")
                    }
                    Button(
                        onClick = { viewModel.deleteNode(node.name) },
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}