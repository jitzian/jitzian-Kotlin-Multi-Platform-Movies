package com.org.jona.kmpmovies.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.org.jona.kmpmovies.ui.screens.Screen
import com.org.jona.kmpmovies.ui.screens.ui.common.LoadingIndicator
import kmpmovies.composeapp.generated.resources.Res
import kmpmovies.composeapp.generated.resources.back
import kmpmovies.composeapp.generated.resources.original_name
import kmpmovies.composeapp.generated.resources.original_title
import kmpmovies.composeapp.generated.resources.popularity
import kmpmovies.composeapp.generated.resources.release_date
import kmpmovies.composeapp.generated.resources.vote_average
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    vm: DetailViewModel,
    onBack: () -> Unit,
) {

    val state by vm.state.collectAsState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Screen {
        Scaffold(
            topBar = {
                DetailTopBar(
                    title = (state as? UIState.Success)?.movie?.title ?: "",
                    onBack = onBack,
                    scrollBehavior = scrollBehavior,
                )
            }
        ) { paddingValues ->
            MovieDetail(
                state = state,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailTopBar(
    title: String,
    onBack: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        title = { Text(title, maxLines = 1) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(Res.string.back)
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
private fun MovieDetail(
    state: UIState,
    modifier: Modifier = Modifier,
) {
    when (state) {
        UIState.Loading -> LoadingIndicator(modifier = modifier)

        is UIState.Success -> Column(
            modifier = modifier.verticalScroll(rememberScrollState()).fillMaxWidth()
        ) {
            with(state) {
                AsyncImage(
                    model = movie.backdrop ?: movie.poster,
                    contentDescription = movie.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16 / 9f)
                )
                Text(
                    text = movie.overview,
                    modifier = Modifier.padding(16.dp),
                )
                Text(
                    text = buildAnnotatedString {
                        property(stringResource(Res.string.original_name), movie.originalLanguage)
                        property(stringResource(Res.string.original_title), movie.title)
                        property(stringResource(Res.string.release_date), movie.releaseDate)
                        property(stringResource(Res.string.popularity), movie.popularity.toString())
                        property(
                            stringResource(Res.string.vote_average),
                            movie.voteAverage.toString(),
                            end = true
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.secondaryContainer)
                        .padding(16.dp)
                )
            }
        }

        else -> Unit
    }
}

private fun AnnotatedString.Builder.property(
    name: String,
    value: String,
    end: Boolean = false
) {
    withStyle(ParagraphStyle(lineHeight = 18.sp)) {
        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
            append("$name :")
        }
        append(value)
        if (!end) append("\n")
    }
}
