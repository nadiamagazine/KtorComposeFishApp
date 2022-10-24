package com.example.ktorcomposeapp.service

import com.example.ktorcomposeapp.model.SpeciesDetailedInfo
import com.example.ktorcomposeapp.model.SpeciesNameAndImage
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

interface KtorService {

    suspend fun getListOfSpeciesNames(): List<SpeciesNameAndImage>

    suspend fun getSpeciesDetailedInfo(speciesName: String): List<SpeciesDetailedInfo>

    companion object {

        fun create(): KtorService {
            return KtorServiceImplementation(
                client = HttpClient(CIO) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(ContentNegotiation) {
                        json(Json {
                            prettyPrint = true
                            isLenient = true
                            ignoreUnknownKeys = true
                        }
                        )
                    }
                }
            )
        }
    }
}
