package com.example.algovisualizer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.algovisualizer.data.local.entity.AlgorithmEntity

@Dao
interface AlgorithmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlgorithm(algo: AlgorithmEntity)

    @Query("SELECT * FROM algorithms")
    suspend fun getAllAlgorithms(): List<AlgorithmEntity>
}