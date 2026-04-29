package com.example.algovisualizer.data.local.mapper

import com.example.algovisualizer.data.local.entity.StepEntity
import com.example.algovisualizer.domain.model.Step

fun StepEntity.toDomain(): Step{
    return Step(
        id = id,
        algorithmId = algorithmId,
        stepNumber = stepNumber,
        explanation = explanation,
        state = state.split(",").map { it.toInt() }
    )
}

fun Step.toEntity() : StepEntity{
    return StepEntity(
        id = id,
        algorithmId = algorithmId,
        stepNumber = stepNumber,
        explanation = explanation,
        state = state.joinToString(",")
    )
}