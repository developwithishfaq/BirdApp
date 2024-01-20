package data.repository

import data.dto.BirdImage
import data.dto.toBirdModel
import domain.BirdsLoaderRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import model.BirdsModel

class BirdsLoaderRepositoryImpl : BirdsLoaderRepository {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    override suspend fun loadAllBirds(): List<BirdsModel> {
        return withContext(Dispatchers.IO) {
            val allImages =
                httpClient.get("https://sebi.io/demo-image-api/pictures.json")
                    .body<List<BirdImage>>()
            allImages.map {
                it.toBirdModel()
            }
        }

    }
}