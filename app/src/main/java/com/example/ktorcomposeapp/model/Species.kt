package com.example.ktorcomposeapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpeciesNameAndImage(
    @SerialName("Species Name") val speciesName: String,
    @SerialName("Species Illustration Photo") val speciesIllustrationPhoto: SpeciesIllustrationImage?
)

@Serializable
data class SpeciesDetailedInfo(
    @SerialName("Species Name") val speciesName: String,
    @SerialName("Harvest Type") val harvestType: String,
    @SerialName("Habitat Impacts") val habitatImpacts: String?,
    @SerialName("Source") val source: String,
    @SerialName("Biology") val biology: String,
    @SerialName("Health Benefits") val healthBenefits: String,
)

@Serializable
data class SpeciesIllustrationImage(
    val src: String,
    val alt: String?,
    val title: String?
)
