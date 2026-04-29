package com.example.algovisualizer.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.algovisualizer.data.local.dao.AlgorithmDao
import com.example.algovisualizer.data.local.dao.UserProgressDao
import com.example.algovisualizer.data.local.dao.stepDao
import com.example.algovisualizer.data.local.entity.AlgorithmEntity
import com.example.algovisualizer.data.local.entity.StepEntity
import com.example.algovisualizer.data.local.entity.UserProgressEntity

@Database(
    entities = [
        AlgorithmEntity:: class,
        StepEntity:: class,
        UserProgressEntity:: class
    ],
    version = 1
)
abstract class AlgoDatabase : RoomDatabase(){

    abstract fun algorithmDao() : AlgorithmDao
    abstract  fun stepDao() : stepDao
    abstract  fun  progressDao() : UserProgressDao
}