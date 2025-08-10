package com.practicum.denettest.domain

import com.practicum.denettest.data.AppMetaRepository

class SetAppMetaUseCase(private val repository: AppMetaRepository) {
    suspend operator fun invoke(key: String, value: String) {
        repository.setValue(key, value)
    }
}