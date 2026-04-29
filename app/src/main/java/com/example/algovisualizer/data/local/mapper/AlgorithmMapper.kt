package com.example.algovisualizer.data.local.mapper

import com.example.algovisualizer.data.local.entity.AlgorithmEntity
import com.example.algovisualizer.domain.model.Algorithm

fun AlgorithmEntity.toDomain(): Algorithm{
    return Algorithm(
        id = id,
        name = name,
        category = category,
        description = description,
    )
}

fun Algorithm.toEntity(): AlgorithmEntity{
    return AlgorithmEntity(
        id = id,
        name = name,
        category = category,
        description = description,
    )
}
