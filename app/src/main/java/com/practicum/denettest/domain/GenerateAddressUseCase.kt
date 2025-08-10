package com.practicum.denettest.domain

import com.practicum.denettest.data.NodeRepository

class GenerateAddressUseCase(private val repository: NodeRepository) {
    operator fun invoke() = repository.generateEthereumAddress()
}