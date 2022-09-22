package com.example.ktorcomposeapp

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*


suspend fun getListOfSpeciesNames(): FishResponse {

    val client = HttpClient(CIO) {
        install(HttpTimeout) {
            requestTimeoutMillis = 1000
        }
        install(ContentNegotiation) {
            json(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        HttpResponseValidator {
            handleResponseExceptionWithRequest { exception, request ->
                val clientException = exception as? ClientRequestException ?: return@handleResponseExceptionWithRequest
                val exceptionResponse = clientException.response
                if (exceptionResponse.status == HttpStatusCode.NotFound) {
                    val exceptionResponseText = exceptionResponse.bodyAsText()
                    throw ServerResponseException(exceptionResponse, exceptionResponseText)
                }
            }
        }
    }

    val listOfFish: List<SpeciesName> = client.get("https://www.fishwatch.gov/api/species").body()
    return FishResponse(listOfFish)

}
