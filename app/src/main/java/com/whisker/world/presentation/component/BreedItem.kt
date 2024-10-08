package com.whisker.world.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.whisker.world.domain.model.Breed

@Composable
fun BreedItem(
    breed: Breed,
    onDetailsClicked: (id: String) -> Unit,
    onFavouriteChanged: (breed: Breed) -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        onClick = {
            onDetailsClicked(breed.id)
        },
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .size(width = 160.dp, 180.dp)
    ) {
        Box {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                CustomSubcomposeAsyncImage(breed.imageUrl, 85)
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = breed.name,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
            }
            CustomIconButton(
                breed = breed,
                onFavouriteChanged = { onFavouriteChanged(breed) },
                modifier = Modifier.align(Alignment.TopEnd)
            )
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
            temperament = "Active, Agile, Clever, Sociable, Loving, Energetic",
            imageId = ""
        ),
        onDetailsClicked = {},
        onFavouriteChanged = {}
    )
}