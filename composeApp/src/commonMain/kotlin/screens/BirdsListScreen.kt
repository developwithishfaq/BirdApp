package screens

import screens.home_screen.BirdsViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import common.Common.BASE_URL
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource


@Composable
fun BirdsListScreen(
    birdsViewModel: BirdsViewModel,
    uiState: BirdsViewModel.BirdsUiState,
    forFav: Boolean = false,
    moveToPhotoViewer: (Int) -> Unit
) {

    val birds = if (forFav) {
        uiState.birdsList?.filter { it.isFavourite }
    } else {
        uiState.birdsList
    }

    birds?.let {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()
        )
        {
            itemsIndexed(birds) { index, item ->
                val path = BASE_URL + item.path

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(20))
                        .clickable {
                            moveToPhotoViewer.invoke(index)
                        },
                ) {
                    KamelImage(
                        asyncPainterResource(path),
                        contentDescription = item.author,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize(),
                        onLoading = {
                            CircularProgressIndicator()
                        }
                    )
                    Image(
                        imageVector = if (item.isFavourite) {
                            Icons.Default.Favorite
                        } else {
                            Icons.Default.FavoriteBorder
                        },
                        contentDescription = null,
                        modifier = Modifier
                            .padding(10.dp)
                            .size(20.dp)
                            .align(Alignment.TopEnd)
                            .clickable {
                                birdsViewModel.addToFav(item)
                            },
                        colorFilter = ColorFilter.tint(Color.Red)
                    )
                }
            }
        }
    } ?: run {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}