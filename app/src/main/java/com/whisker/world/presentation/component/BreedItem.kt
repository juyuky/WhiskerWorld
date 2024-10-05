package com.whisker.world.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.whisker.world.R
import com.whisker.world.domain.model.Breed
import com.whisker.world.presentation.home.HomeEvent

@Composable
fun BreedItem(
    breed: Breed,
    onEvent: (HomeEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        onClick = {
            onEvent(HomeEvent.OnDetailsClicked(breed.id))
        },
        modifier = modifier
            .padding(8.dp, 16.dp)
            .size(width = 160.dp, 180.dp)
    ) {
        Box {
            IconToggleButton(
                checked = false/* breed.isFavorite*/,
                onCheckedChange = {
                    //breed.isFavorite = !breed.isFavorite
                },
                modifier = modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = if (false) { //if (breed.isFavorite) {
                        Icons.Filled.Star
                    } else {
                        Icons.Default.Star
                    },
                    contentDescription =
                    stringResource(R.string.breed_item_favourites_content_description)
                )
            }
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = modifier.height(32.dp))
                CustomSubcomposeAsyncImage(breed.imageUrl, 85)
                Spacer(modifier = modifier.height(12.dp))
                Text(
                    text = breed.name,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun BreedItemPreview() {
    BreedItem(
        breed = Breed(
            id = "123",
            name = "Siamese",
            description = "While Siamese cats are extremely fond of their people, they will follow you around and supervise your every move, being talkative and opinionated. They are a demanding and social cat, that do not like being left alone for long periods",
            imageUrl = "https://example.com/image.jpg",
            origin = "Thailand",
            temperament = "Active, Agile, Clever, Sociable, Loving, Energetic"
        ),
        onEvent = {}
    )
}