package com.example.algovisualizer.data.repository

import com.example.algovisualizer.data.local.dao.AlgorithmDao
import com.example.algovisualizer.data.local.dao.stepDao
import com.example.algovisualizer.data.local.mapper.toDomain
import com.example.algovisualizer.data.local.mapper.toEntity
import com.example.algovisualizer.domain.model.Algorithm
import com.example.algovisualizer.domain.model.Step
import com.example.algovisualizer.domain.repository.AlgoRepository

class AlgoRepositoryImpl(
    private  val dao: AlgorithmDao,
    private val stepDao: stepDao
): AlgoRepository {

    override suspend fun getAlgorithms(): List<Algorithm> {
        return dao.getAllAlgorithms().map { it.toDomain() }
    }

    override suspend fun getSteps(algoId: Int): List<Step> {
        return stepDao.getSteps(algoId).map { it.toDomain() }
    }

    override suspend fun insertAlgorithm(algorithm: Algorithm) {
          dao.insertAlgorithm(algorithm.toEntity())
    }

    override suspend fun insertStep(step: List<Step>) {
        stepDao.insertSteps(step.map { it.toEntity() })
    }

}