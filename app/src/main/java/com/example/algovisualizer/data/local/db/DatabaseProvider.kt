package com.example.algovisualizer.data.local.db

import android.content.Context
import androidx.room.Room

object DatabaseProvider{

    @Volatile
    private var INSTANCE : AlgoDatabase? = null

    fun getDatabase(context : Context): AlgoDatabase{
        return INSTANCE ?: synchronized(this){
            val instance  = Room.databaseBuilder(
                context.applicationContext,
                AlgoDatabase::class.java,
                "algo_db"
            ).build()

            INSTANCE = instance
            instance
        }
    }

}