package com.example.algovisualizer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "algorithms")
data class AlgorithmEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val category : String,
    val description : String
)