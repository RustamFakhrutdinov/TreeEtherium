package com.practicum.denettest.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "nodes",
    foreignKeys = [ForeignKey(
        entity = Node::class,
        parentColumns = ["name"],
        childColumns = ["parentName"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Node(
    @PrimaryKey val name: String,
    val parentName: String? = null
)