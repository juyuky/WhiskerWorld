package com.whisker.world.presentation.favourite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.whisker.world.R
import com.whisker.world.presentation.component.BaseScreen
import com.whisker.world.presentation.component.BreedItem
import com.whisker.world.presentation.ui.theme.WhiskerWorldTheme

@Composable
fun FavouriteScreen(
    currentRoute: String?,
    state: FavouriteState,
    onEvent: (FavouriteEvent) -> Unit
) {
    BaseScreen(
        currentRoute = currentRoute,
        showError = state.showError,
        onNavigationBarItemClicked = {
            onEvent(FavouriteEvent.OnNavigationBarItemClicked)
        }
    ) {
        if (state.favouritesBreedList.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.favourites_screen_empty_title),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.favourites_screen_empty_description),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(120.dp),
                content = {
                    items(state.favouritesBreedList) { breed ->
                        BreedItem(
                            breed = breed,
                            onDetailsClicked = {
                                onEvent(FavouriteEvent.OnDetailsClicked(it))
                            },
                            onFavouriteChanged = {
                                onEvent(FavouriteEvent.OnFavouriteChange(it))
                            }
                        )
                    }
                },
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun FavouriteScreenPreview() {
    WhiskerWorldTheme {
        FavouriteScreen("", FavouriteState()) { }
    }
}
