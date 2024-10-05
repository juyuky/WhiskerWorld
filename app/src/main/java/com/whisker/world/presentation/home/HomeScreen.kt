package com.whisker.world.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.whisker.world.R
import com.whisker.world.navigation.getBottomNavigationItems
import com.whisker.world.presentation.component.BreedItem
import com.whisker.world.presentation.component.CustomTopBar
import com.whisker.world.presentation.ui.theme.WhiskerWorldTheme

@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                CustomTopBar(R.string.home_screen_top_bar_title)
            },
            content = { innerPadding ->
                Column(modifier = Modifier.padding(innerPadding)) {
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
                                BreedItem(breed, onEvent)
                            }
                        },
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                    )
                }
            },
            bottomBar = {
                GetNavigationBar(onEvent)
            }
        )
    }
}

@Composable
fun GetNavigationBar(onEvent: (HomeEvent) -> Unit) {
    val navigationItems = getBottomNavigationItems()
    var selectedBarItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    NavigationBar {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedBarItemIndex == index,
                onClick = {
                    selectedBarItemIndex = index
                    onEvent(HomeEvent.OnNavigationBarItemClicked)
                },
                label = {
                    Text(text = item.title)
                },
                alwaysShowLabel = false,
                icon = {
                    Icon(
                        imageVector = if (index == selectedBarItemIndex) {
                            item.selectedIcon
                        } else {
                            item.unselectedIcon
                        },
                        contentDescription = item.title
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    WhiskerWorldTheme {
        HomeScreen(HomeState()) { }
    }
}
