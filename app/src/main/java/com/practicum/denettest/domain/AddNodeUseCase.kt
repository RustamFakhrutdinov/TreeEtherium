package com.practicum.denettest.domain

import com.practicum.denettest.data.NodeRepository

class AddNodeUseCase(private val repository: NodeRepository) {
    suspend operator fun invoke(node: Node) = repository.addNode(node)
}