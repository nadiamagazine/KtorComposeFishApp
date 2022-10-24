package com.example.ktorcomposeapp.service

import com.example.ktorcomposeapp.model.SpeciesDetailedInfo
import com.example.ktorcomposeapp.model.SpeciesNameAndImage
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import timber.log.Timber

class KtorServiceImplementation(
    private val client: HttpClient
) : KtorService {

    override suspend fun getListOfSpeciesNames(): List<SpeciesNameAndImage> {
        return try {
            client.get("https://www.fishwatch.gov/api/species").body()
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            Timber.d("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            // 4xx - responses
            Timber.d("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            // 5xx - responses
            Timber.d("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            Timber.d("Error: ${e.message}")
            emptyList()
        }
    }
}
