package com.whisker.world.presentation.favourite

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@Preview
@Composable
fun FavouriteScreenPreview() {
    WhiskerWorldTheme {
        FavouriteScreen("", FavouriteState()) { }
    }
}
