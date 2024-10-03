package com.whisker.world.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.whisker.world.R
import com.whisker.world.presentation.BreedUi

@Composable
fun BreedItem(
    breedUi: BreedUi,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .padding(8.dp, 16.dp)
            .size(width = 160.dp, 180.dp)
    ) {
        Box {
            IconToggleButton(
                checked = breedUi.isFavorite,
                onCheckedChange = {
                    breedUi.isFavorite = !breedUi.isFavorite
                },
                modifier = modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = if (breedUi.isFavorite) {
                        Icons.Filled.Star
                    } else {
                        Icons.Default.Star
                    },
                    contentDescription =
                    stringResource(R.string.breed_item_favourites_content_description)
                )
            }
            Column(
                modifier = modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = modifier.height(32.dp))
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(breedUi.imageUrl)
                        .crossfade(true)
                        .build(),
                    loading = {
                        CircularProgressIndicator(color = Color.LightGray)
                    },
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(85.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = modifier.height(12.dp))
                Text(
                    text = breedUi.name,
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
        breedUi = BreedUi(
            name = "Siamese",
            imageUrl = "https://example.com/image.jpg",
            isFavorite = false
        )
    )
}