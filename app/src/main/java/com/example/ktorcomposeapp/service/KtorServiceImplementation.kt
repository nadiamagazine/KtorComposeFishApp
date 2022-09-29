package com.example.ktorcomposeapp.service

import com.example.ktorcomposeapp.model.SpeciesResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*

class KtorServiceImplementation(
    private val client: HttpClient
): KtorService {

    override suspend fun getListOfSpeciesNames(): List<SpeciesResponse> {
        return try {
            client.get("https://www.fishwatch.gov/api/species").body()
        } catch(e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch(e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch(e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch(e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }
}
