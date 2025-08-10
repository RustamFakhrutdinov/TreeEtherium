package com.practicum.denettest.domain

import com.practicum.denettest.data.AppMetaRepository

class GetAppMetaUseCase(private val repository: AppMetaRepository) {
    suspend operator fun invoke(key: String): String? {
        return repository.getValue(key)
    }
}