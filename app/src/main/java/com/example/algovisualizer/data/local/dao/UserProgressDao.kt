package com.example.algovisualizer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.algovisualizer.data.local.entity.UserProgressEntity

@Dao
interface UserProgressDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProgress(progress : UserProgressEntity)

    @Query("SELECT * FROM user_progress WHERE userId = :userId")
    suspend fun getUserProgress(userId : String): List<UserProgressEntity>

}