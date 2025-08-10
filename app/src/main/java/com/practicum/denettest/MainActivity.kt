package com.practicum.denettest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.practicum.denettest.data.AppDatabase
import com.practicum.denettest.data.AppMetaRepository
import com.practicum.denettest.data.NodeRepository
import com.practicum.denettest.domain.AddNodeUseCase
import com.practicum.denettest.domain.DeleteNodeUseCase
import com.practicum.denettest.domain.GenerateAddressUseCase
import com.practicum.denettest.domain.GetAppMetaUseCase
import com.practicum.denettest.domain.GetNodeUseCase
import com.practicum.denettest.domain.SetAppMetaUseCase
import com.practicum.denettest.presentation.TreeScreen
import com.practicum.denettest.presentation.TreeViewModel
//import com.practicum.denettest.presentation.ui.theme.DeNetTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(this)

        val nodeRepository = NodeRepository(database.nodeDao())
        val appMetaRepository = AppMetaRepository(database.appMetaDao())

        val getNodeUseCase = GetNodeUseCase(nodeRepository)
        val addNodeUseCase = AddNodeUseCase(nodeRepository)
        val deleteNodeUseCase = DeleteNodeUseCase(nodeRepository)
        val getAppMetaUseCase = GetAppMetaUseCase(appMetaRepository)
        val setAppMetaUseCase = SetAppMetaUseCase(appMetaRepository)
        val generateAddressUseCase = GenerateAddressUseCase(nodeRepository)

        val viewModel = TreeViewModel(
            getNodeUseCase,
            addNodeUseCase,
            deleteNodeUseCase,
            getAppMetaUseCase,
            setAppMetaUseCase,
            generateAddressUseCase
        )

        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TreeScreen(viewModel)
                }
            }
        }
    }
}