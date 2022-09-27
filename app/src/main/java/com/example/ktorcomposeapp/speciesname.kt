package com.example.ktorcomposeapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpeciesName(
    @SerialName("Species Name") val speciesName: String,
    @SerialName("Harvest Type") val harvestType: String,
    @SerialName("Habitat Impacts") val habitatImpacts: String?,
    @SerialName("Species Illustration Photo") val speciesIllustrationPhoto: SpeciesIllustrationImage?
)

@Serializable
data class FishResponse(
    val listOfFish: List<SpeciesName>
)

@Serializable
data class SpeciesIllustrationImage(
    val src: String,
    val alt: String?,
    val title: String?
)
