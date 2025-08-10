package com.practicum.denettest.domain

import com.practicum.denettest.data.NodeRepository

class DeleteNodeUseCase(private val repository: NodeRepository) {
    suspend operator fun invoke(name: String) = repository.deleteNode(name)
}