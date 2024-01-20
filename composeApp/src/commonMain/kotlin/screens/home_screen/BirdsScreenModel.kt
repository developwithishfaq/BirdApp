package screens.home_screen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import domain.BirdsLoaderRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.BirdsModel

class BirdsScreenModel(
    private val repository: BirdsLoaderRepository
) : StateScreenModel<BirdsScreenModel.BirdsUiState>(BirdsUiState()) {
    data class BirdsUiState(
        val birdsList: List<BirdsModel>? = null
    )

    init {
        loadImages()
    }

    private fun loadImages() {
        println("Loading Images")
        screenModelScope.launch {
            val images = repository.loadAllBirds()
            mutableState.update {
                it.copy(
                    birdsList = images
                )
            }
        }
    }

    fun addToFav(item: BirdsModel) {
        CoroutineScope(Dispatchers.Default).launch {
            val mainList = state.value.birdsList?.toMutableList() ?: mutableListOf()
            val index = mainList.indexOfFirst {
                it.path == item.path
            }
            mainList.removeAt(index)
            mainList.add(index, item.copy(isFavourite = !item.isFavourite))
            mutableState.update {
                it.copy(
                    birdsList = mainList
                )
            }
        }
    }
}