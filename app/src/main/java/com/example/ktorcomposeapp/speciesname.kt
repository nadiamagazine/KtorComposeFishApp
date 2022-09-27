package com.example.ktorcomposeapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpeciesName(
    @SerialName("Species Name") val speciesName: String,
    @SerialName("Harvest Type") val harvestType: String,
    @SerialName("Habitat Impacts") val habitatImpacts: String?
)

@Serializable
data class FishResponse(
    val listOfFish: List<SpeciesName>
)
