package data.dto

import kotlinx.serialization.Serializable
import model.BirdsModel

@Serializable
data class BirdImage(
    val category: String,
    val path: String,
    val author: String
)

fun BirdImage.toBirdModel() = BirdsModel(this.category, this.path, this.author, false)