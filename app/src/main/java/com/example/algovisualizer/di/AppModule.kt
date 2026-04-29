package com.example.algovisualizer.di

import android.content.Context
import com.example.algovisualizer.data.local.db.AlgoDatabase
import com.example.algovisualizer.data.local.db.DatabaseProvider
import com.example.algovisualizer.data.repository.AlgoRepositoryImpl
import com.example.algovisualizer.domain.repository.AlgoRepository
import com.example.algovisualizer.domain.usecase.algorithm.AlgoUseCases
import com.example.algovisualizer.domain.usecase.algorithm.GetAlgorithmsUseCase
import com.example.algovisualizer.domain.usecase.algorithm.GetStepsUseCase
import com.example.algovisualizer.domain.usecase.algorithm.InsertAlgorithmUseCase
import com.example.algovisualizer.domain.usecase.algorithm.InsertStepUseCase

object AppModule {

    fun providerDatabase(context: Context): AlgoDatabase{
        return DatabaseProvider.getDatabase(context)
    }

    fun provideRepository(db: AlgoDatabase): AlgoRepository {
        return AlgoRepositoryImpl(
            dao = db.algorithmDao(),
            stepDao = db.stepDao()
        )
    }

    fun provideUseCases(repository: AlgoRepository) : AlgoUseCases{
        return AlgoUseCases(
            getAlgorithms = GetAlgorithmsUseCase(repository),
            getSteps = GetStepsUseCase(repository),
            insertAlgorithm = InsertAlgorithmUseCase(repository),
            insertStep = InsertStepUseCase(repository)
        )
    }

}