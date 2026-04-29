package com.example.algovisualizer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "steps")
data class StepEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val algorithmId: Int,
    val stepNumber: Int,
    val explanation: String,
    val state: String

)