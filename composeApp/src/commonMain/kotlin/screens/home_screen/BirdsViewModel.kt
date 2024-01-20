package screens.home_screen

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import domain.BirdsLoaderRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import model.BirdsModel


class BirdsViewModel(
    private val repository: BirdsLoaderRepository
) : ViewModel() {

    private val _state = MutableStateFlow(BirdsUiState())
    val state = _state.asStateFlow()

    data class BirdsUiState(
        val birdsList: List<BirdsModel>? = null
    )

    init {
        loadImages()
    }

    private fun loadImages() {
        println("Loading Images")
        viewModelScope.launch {
            val images = repository.loadAllBirds()
            _state.update {
                it.copy(
                    birdsList = images
                )
            }
        }
    }

    fun addToFav(birdsModel: BirdsModel) {
        CoroutineScope(Dispatchers.Default).launch {
            val mainList = state.value.birdsList?.toMutableList() ?: mutableListOf()
            val index = mainList.indexOfFirst {
                it.path == birdsModel.path
            }
            mainList.removeAt(index)
            mainList.add(index, birdsModel.copy(isFavourite = !birdsModel.isFavourite))
            _state.update {
                it.copy(
                    birdsList = mainList
                )
            }
        }
    }
}