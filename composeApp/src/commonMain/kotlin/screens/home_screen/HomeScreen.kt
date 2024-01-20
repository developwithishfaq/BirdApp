package screens.home_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import domain.BirdsLoaderRepository
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import screens.BirdsListScreen
import screens.ImageViewerScreen
import screens.home_screen.components.BottomBar
import screens.home_screen.components.TopBar

class HomeScreen : Screen {

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val repository = koinInject<BirdsLoaderRepository>()

        val viewModel = getViewModel(Unit, viewModelFactory {
            BirdsViewModel(repository)
        })

        val pagerState = rememberPagerState {
            2
        }
        val coroutineScope = rememberCoroutineScope()
        Scaffold(
            bottomBar = {
                BottomBar(pagerState.currentPage, select = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(it)
                    }
                })
            },
            topBar = {
                TopBar()
            },
            modifier = Modifier
                .fillMaxSize()
        ) {

            val uiState by viewModel.state.collectAsState()

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize().padding(it)
            ) {
                BirdsListScreen(
                    birdsViewModel = viewModel,
                    forFav = it == 1,
                    uiState = uiState,
                    moveToPhotoViewer = {
                        navigator?.push(ImageViewerScreen(it, uiState.birdsList ?: emptyList()))
                    }
                )
            }
        }
    }

}