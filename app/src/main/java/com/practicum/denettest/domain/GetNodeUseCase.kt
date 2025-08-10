package com.practicum.denettest.domain

import com.practicum.denettest.data.NodeRepository
import kotlinx.coroutines.flow.Flow

class GetNodeUseCase(private val repository: NodeRepository) {
    fun getChildren(parentName: String?): Flow<List<Node>> = repository.getChildren(parentName)

    suspend fun getNodeByName(name: String): Node? = repository.getNodeByName(name)
}