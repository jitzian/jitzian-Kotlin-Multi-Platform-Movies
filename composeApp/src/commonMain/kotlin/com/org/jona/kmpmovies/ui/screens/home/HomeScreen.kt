import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.org.jona.kmpmovies.ui.screens.data.Movie
import com.org.jona.kmpmovies.ui.screens.Screen
import com.org.jona.kmpmovies.ui.screens.UIState
import com.org.jona.kmpmovies.ui.screens.home.HomeViewModel
import com.org.jona.kmpmovies.ui.screens.home.LoadingScreen
import kmpmovies.composeapp.generated.resources.Res
import kmpmovies.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onMovieClick: (Movie) -> Unit,
    //vm: HomeViewModel = viewModel { HomeViewModel() }
    vm: HomeViewModel,
) {

    val state by vm.state.collectAsState()
    Screen {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(Res.string.app_name)) },
                    scrollBehavior = scrollBehavior
                )
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { paddingValues ->
            when (state) {
                UIState.Loading -> LoadingScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                )

                UIState.Empty -> Unit
                is UIState.Error -> Unit
                is UIState.Success -> LazyVerticalGrid(
                    columns = GridCells.Adaptive(128.dp),
                    contentPadding = PaddingValues(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(paddingValues)
                ) {
                    items((state as UIState.Success).movies, key = { it.id }) { movie ->
                        MovieItem(
                            movie = movie,
                            onMovieClick = { onMovieClick(movie) }
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun MovieItem(
    movie: Movie,
    onMovieClick: () -> Unit,
) {
    Column(modifier = Modifier.clickable { onMovieClick() }) {
        AsyncImage(
            model = movie.poster,
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2 / 3f)
                .clip(MaterialTheme.shapes.small)
        )
        Text(
            text = movie.title,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 2,
            modifier = Modifier.padding(8.dp)
        )

    }
}
