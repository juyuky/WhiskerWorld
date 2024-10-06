package com.whisker.world.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.whisker.world.R
import com.whisker.world.domain.model.Breed

@Composable
fun CustomIconButton(
    breed: Breed,
    onFavouriteChanged: (breed: Breed) -> Unit,
    modifier: Modifier = Modifier
) {

    var isFavourite by remember { mutableStateOf(breed.isFavourite) }
    IconButton(
        onClick = {
            isFavourite = !isFavourite
            breed.isFavourite = isFavourite
            onFavouriteChanged(breed)
        },
        modifier = modifier
    ) {
        Icon(
            imageVector = if (isFavourite) {
                Icons.Outlined.Favorite
            } else {
                Icons.Outlined.FavoriteBorder
            },
            contentDescription = if (isFavourite) {
                stringResource(R.string.breed_item_favourites_remove_content_description)
            } else {
                stringResource(R.string.breed_item_favourites_add_content_description)
            }
        )
    }
}