package com.example.moviesapptask.ui.features.movieDetails.composable

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.moviesapptask.R
import com.example.moviesapptask.data.datasource.dto.moviedetails.Genre
import com.example.moviesapptask.data.datasource.dto.moviedetails.SpokenLanguage
import com.example.moviesapptask.ui.shared.uimodel.MovieDetailsUIModel
import com.example.moviesapptask.ui.shared.uimodel.movieDetailsMock
import com.example.moviesapptask.ui.theme.MoviesAppTaskTheme


@Composable
fun MovieDetailsContent(
    modifier: Modifier = Modifier,
    movieDetails: MovieDetailsUIModel,
    onBackButtonClicked: () -> Unit
) {

    BackHandler(onBack = onBackButtonClicked)

    Box(modifier = modifier) {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            ConstraintLayout(Modifier) {


                val (image) = createRefs()
                MovieDetailsImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(image) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    imagePath = movieDetails.backdropPath ?: "",
                    tags = emptyList(),
                )
            }
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                MovieTitle(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    title = movieDetails.title ?: ""
                )
                Spacer(modifier = Modifier.height(8.dp))
                MovieOverview(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    overviewText = movieDetails.overview ?: ""
                )
                Spacer(modifier = Modifier.height(16.dp))
                MovieDetails(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    time = movieDetails.runtime.toString(),
                    rate = movieDetails.voteAverage,
                    releaseDate = movieDetails.releaseDate,
                    popularity = movieDetails.popularity,
                )
                Spacer(modifier = Modifier.height(16.dp))
                MovieGenresAndLang(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    spokenLanguages = movieDetails.spokenLanguages ?: emptyList(),
                    genres = movieDetails.genres ?: emptyList()
                )
            }
        }
        MovieDetailsToolbar(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            onBackClick = onBackButtonClicked
        )
    }
}

@Composable
fun MovieGenresAndLang(
    modifier: Modifier,
    spokenLanguages: List<SpokenLanguage?>,
    genres: List<Genre?>
) {
    Column(modifier) {
        if(spokenLanguages.isNotEmpty()){
            Text(
                text = stringResource(id = R.string.spoken_languages, spokenLanguages.map { it?.englishName }),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        if(genres.isNotEmpty()){
            Text(
                text = stringResource(id = R.string.genres, genres.map { it?.name }),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
@Composable
fun MovieDetails(
    modifier: Modifier,
    time: String?,
    rate: Double?,
    releaseDate: String?,
    popularity: Double?,
) {
    Row(modifier = modifier) {
        Column(modifier = Modifier.weight(1f)) {
            time?.let {
                Text(
                    text = stringResource(id = R.string.movie_time, time),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            releaseDate?.let {
                Text(
                    text = stringResource(id = R.string.release_date,releaseDate),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        Column(modifier = Modifier.weight(1f)) {
            rate?.let {
                Text(
                    text = stringResource(id = R.string.vote_average, rate),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            popularity?.let {
                Text(
                    text = stringResource(id = R.string.popularity,popularity),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}


@Composable
private fun MovieOverview(modifier: Modifier = Modifier, overviewText: String) {
    Text(
        modifier = modifier,
        text = overviewText,
        textAlign = TextAlign.Start,
        style = MaterialTheme.typography.bodyMedium
    )
}


@Composable
private fun MovieTitle(modifier: Modifier = Modifier, title: String) {
    Text(
        modifier = modifier,
        text = title,
        textAlign = TextAlign.Start,
        style = MaterialTheme.typography.headlineMedium
    )
}


@Preview(heightDp = 1500, widthDp = 400)
@Composable
private fun NewsDetailsContentPreview() {
    MoviesAppTaskTheme {
        MovieDetailsContent(
            modifier = Modifier,
            movieDetails = movieDetailsMock(),
            onBackButtonClicked = {}
        )
    }
}


