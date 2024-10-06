package com.whisker.world.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.whisker.world.R
import com.whisker.world.presentation.component.BaseScreen
import com.whisker.world.presentation.component.BreedItem
import com.whisker.world.presentation.ui.theme.WhiskerWorldTheme

@Composable
fun HomeScreen(
    currentRoute: String?,
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    BaseScreen(
        currentRoute = currentRoute,
        showError = state.showError,
        onNavigationBarItemClicked = {
            onEvent(HomeEvent.OnNavigationBarItemClicked)
        }
    ) {
        TextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                onEvent(HomeEvent.OnSearch(searchQuery))
            },
            placeholder = {
                Text(text = stringResource(R.string.home_screen_search_placeholder))
            },
            colors = TextFieldDefaults.colors().copy(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(120.dp),
            content = {
                items(state.breedUiList) { breed ->
                    BreedItem(
                        breed = breed,
                        onDetailsClicked = {
                            onEvent(HomeEvent.OnDetailsClicked(it))
                        },
                        onFavouriteChanged = {
                            onEvent(HomeEvent.OnFavouriteChange(it))
                        }
                    )
                }
            },
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    WhiskerWorldTheme {
        HomeScreen("", HomeState()) { }
    }
}
