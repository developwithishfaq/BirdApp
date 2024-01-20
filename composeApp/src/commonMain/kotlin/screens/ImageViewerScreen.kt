package screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import common.Common.BASE_URL
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import data.dto.BirdImage
import model.BirdsModel

class ImageViewerScreen(val index: Int, val list: List<Any>) : Screen {

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val pagerState = rememberPagerState(initialPage = index) {
            list.size
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
        ) {
            val model = list[it]
            if (model is BirdsModel) {
                val path = BASE_URL + model.path
                KamelImage(
                    asyncPainterResource(path),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}