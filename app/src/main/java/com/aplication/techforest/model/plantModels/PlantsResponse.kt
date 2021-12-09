package com.aplication.techforest.model.plantModels

data class PlantsResponse(
    val language: String,
    val preferedReferential: String,
    val query: Query,
    val remainingIdentificationRequests: Int,
    val results: List<Result>
)