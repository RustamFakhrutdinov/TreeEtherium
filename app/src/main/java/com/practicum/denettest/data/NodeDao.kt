package com.practicum.denettest.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.practicum.denettest.domain.Node
import kotlinx.coroutines.flow.Flow

@Dao
interface NodeDao {
    @Query("""
        SELECT * FROM nodes 
        WHERE (:parentName IS NULL AND parentName IS NULL) 
           OR (:parentName IS NOT NULL AND parentName = :parentName)
    """)
    fun getChildren(parentName: String?): Flow<List<Node>>

    @Query("SELECT * FROM nodes WHERE name = :name")
    suspend fun getNodeByName(name: String): Node?

    @Insert
    suspend fun insert(node: Node)

    @Query("DELETE FROM nodes WHERE name = :name")
    suspend fun delete(name: String)
}