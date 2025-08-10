package com.practicum.denettest.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.practicum.denettest.domain.AppMeta

@Dao
interface AppMetaDao {
    @Query("SELECT value FROM app_meta WHERE `key` = :metaKey LIMIT 1")
    suspend fun getValue(metaKey: String): String?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(meta: AppMeta)
}