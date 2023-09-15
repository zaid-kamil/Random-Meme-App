@file:Suppress("UNCHECKED_CAST")

package com.example.randommeme.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.randommeme.R
import com.example.randommeme.model.Meme
import com.example.randommeme.ui.theme.RandomMemeTheme

@Composable
fun HomeScreen(
    memeUiState: MemeUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (memeUiState) {
        is MemeUiState.Success -> {
             MemeList(memes = memeUiState.data, modifier = modifier)
        }

        is MemeUiState.Error -> {
            ErrorScreen(
                modifier = modifier,
                onRetry = retryAction
            )
        }

        is MemeUiState.Loading -> {
            LoadingScreen(modifier = modifier)
        }
    }
}

@Composable
fun MemeList(memes: List<Meme>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(memes) { meme ->
            MemeCard(
                meme = meme,
                onClick = {
                    // TODO: open browser with meme url
                },
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemeCard(
    meme: Meme,
    onClick: (Meme) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        onClick = { onClick(meme) },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(meme.url)
                .build(),
            contentDescription = meme.title,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(1.dp, MaterialTheme.colorScheme.onSurface, MaterialTheme.shapes.medium)
        )
    }
}


@Composable
fun ErrorScreen(onRetry: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(imageVector = Icons.Default.Close, contentDescription = null)
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = onRetry) {
            Text(stringResource(R.string.retry))
        }
    }
}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier.size(200.dp),
        imageVector = Icons.Default.Info,
        contentDescription = stringResource(R.string.loading)
    )
}

@Preview(showBackground = true)
@Composable
fun MemeCardPreview() {
    RandomMemeTheme {
        MemeCard(
            meme = Meme(
                title = "Title",
                url = "https://i.redd.it/1i0r3qj0q8y61.jpg"
            ),
            onClick = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MemeListPreview() {
    RandomMemeTheme {
        MemeList(
            memes = listOf(
                Meme(
                    title = "Title",
                    url = "https://i.redd.it/1i0r3qj0q8y61.jpg"
                ),
                Meme(
                    title = "Title",
                    url = "https://i.redd.it/1i0r3qj0q8y61.jpg"
                ),
            )
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    RandomMemeTheme {
        ErrorScreen({})
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    RandomMemeTheme {
        LoadingScreen()
    }
}
