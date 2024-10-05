package com.whisker.world.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.whisker.world.R
import com.whisker.world.domain.model.Breed
import com.whisker.world.presentation.component.CustomSubcomposeAsyncImage
import com.whisker.world.presentation.component.CustomTopBar
import com.whisker.world.presentation.ui.theme.WhiskerWorldTheme

@Composable
fun BreedDetailsScreen(
    state: BreedDetailsState,
    onEvent: (BreedDetailsEvent) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                CustomTopBar(R.string.details_screen_top_bar_title)
            },
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    CustomSubcomposeAsyncImage(state.breed.imageUrl, 120)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = state.breed.name,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = state.breed.description,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.details_screen_origin_title),
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(start = 10.dp, top = 10.dp)
                        )
                        Text(
                            text = state.breed.origin,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.details_screen_temperament_title),
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(start = 10.dp, top = 10.dp)
                        )
                        Text(
                            text = state.breed.temperament,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    WhiskerWorldTheme {
        BreedDetailsScreen(
            state = BreedDetailsState(
                breed = Breed(
                    id = "123",
                    name = "Siamese",
                    description = "While Siamese cats are extremely fond of their people, they " +
                            "will follow you around and supervise your every move, being talkative " +
                            "and opinionated. They are a demanding and social cat, that do not like " +
                            "being left alone for long periods",
                    imageUrl = "https://example.com/image.jpg",
                    origin = "Thailand",
                    temperament = "Active, Agile, Clever, Sociable, Loving, Energetic"
                )
            ),
            onEvent = {}
        )
    }
}