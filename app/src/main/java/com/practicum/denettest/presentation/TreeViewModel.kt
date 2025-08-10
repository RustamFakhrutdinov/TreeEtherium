package com.practicum.denettest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicum.denettest.domain.AddNodeUseCase
import com.practicum.denettest.domain.DeleteNodeUseCase
import com.practicum.denettest.domain.GenerateAddressUseCase
import com.practicum.denettest.domain.GetAppMetaUseCase
import com.practicum.denettest.domain.GetNodeUseCase
import com.practicum.denettest.domain.Node
import com.practicum.denettest.domain.SetAppMetaUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job


class TreeViewModel(
    private val getNodeUseCase: GetNodeUseCase,
    private val addNodeUseCase: AddNodeUseCase,
    private val deleteNodeUseCase: DeleteNodeUseCase,
    private val getAppMetaUseCase: GetAppMetaUseCase,
    private val setAppMetaUseCase: SetAppMetaUseCase,
    private val generateAddressUseCase: GenerateAddressUseCase
) : ViewModel() {

    private val _nodes = MutableStateFlow<List<Node>>(emptyList())
    val nodes: StateFlow<List<Node>> = _nodes

    private val _currentNodeName = MutableStateFlow<String?>(null)
    val currentNodeName: StateFlow<String?> = _currentNodeName

    private val _currentParentName = MutableStateFlow<String?>(null)
    val currentParentName: StateFlow<String?> = _currentParentName

    private val _rootName = MutableStateFlow<String?>(null)

    private var loadJob: Job? = null

    init {
        loadRootNode()
    }

    private fun loadRootNode() {
        viewModelScope.launch {

            var rootName = getAppMetaUseCase("root_node_name")

            if (rootName == null) {
                rootName = generateAddressUseCase()

                setAppMetaUseCase("root_node_name", rootName)

                addNodeUseCase(Node(name = rootName, parentName = null))
            }

            _rootName.value = rootName
            loadChildren(null)
        }
    }

    fun loadChildren(parentName: String?) {
        loadJob?.cancel()
        loadJob = viewModelScope.launch {
            if (parentName == null) {
                _currentNodeName.value = _rootName.value
                _currentParentName.value = null
                getNodeUseCase.getChildren(_rootName.value).collect { children ->
                    _nodes.value = children
                }
            } else {
                val parent = getNodeUseCase.getNodeByName(parentName)
                _currentNodeName.value = parentName
                _currentParentName.value = parent?.parentName
                getNodeUseCase.getChildren(parentName).collect { children ->
                    _nodes.value = children
                }
            }
        }
    }

    fun addChild(parentName: String?) {
        viewModelScope.launch {
            val newName = generateAddressUseCase()
            addNodeUseCase(Node(name = newName, parentName = parentName))
            loadChildren(parentName)
        }
    }

    fun deleteNode(nodeName: String) {
        viewModelScope.launch {
            deleteNodeUseCase(nodeName)
            if (nodeName == _currentNodeName.value) {
                loadChildren(_currentParentName.value)
            } else {
                loadChildren(_currentNodeName.value)
            }
        }
    }
}