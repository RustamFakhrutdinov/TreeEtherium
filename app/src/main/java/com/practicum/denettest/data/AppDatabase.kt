package com.practicum.denettest.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.practicum.denettest.domain.AppMeta
import com.practicum.denettest.domain.Node

@Database(entities = [Node::class, AppMeta::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun nodeDao(): NodeDao
    abstract fun appMetaDao(): AppMetaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "tree_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}