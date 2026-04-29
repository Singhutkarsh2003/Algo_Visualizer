package com.example.algovisualizer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.algovisualizer.data.local.entity.StepEntity

@Dao
interface stepDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertSteps(steps:List<StepEntity>)

    @Query("SELECT * FROM steps WHERE algorithmId =  :algoId ORDER BY stepNumber")
    suspend fun  getSteps(algoId: Int) : List<StepEntity>

}