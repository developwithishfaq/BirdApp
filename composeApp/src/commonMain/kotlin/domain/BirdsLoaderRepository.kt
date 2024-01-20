package domain

import model.BirdsModel

interface BirdsLoaderRepository {
    suspend fun loadAllBirds(): List<BirdsModel>
}