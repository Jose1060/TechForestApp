package com.aplication.techforest.model.plantModels

data class Species(
    val commonNames: List<String>,
    val family: Family,
    val genus: Genus,
    val scientificNameAuthorship: String,
    val scientificNameWithoutAuthor: String
)