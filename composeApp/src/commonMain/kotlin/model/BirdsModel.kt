package model

import kotlinx.serialization.Serializable

data class BirdsModel(
    val category: String,
    val path: String,
    val author: String,
    val isFavourite: Boolean,
)
