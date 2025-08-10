package com.practicum.denettest.data

import com.practicum.denettest.data.AppMetaDao
import com.practicum.denettest.domain.AppMeta

class AppMetaRepository(private val dao: AppMetaDao) {

    suspend fun getValue(key: String): String? {
        return dao.getValue(key)
    }

    suspend fun setValue(key: String, value: String) {
        dao.insert(AppMeta(key, value))
    }
}