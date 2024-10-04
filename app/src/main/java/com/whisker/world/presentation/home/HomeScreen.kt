package com.whisker.world.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsIgnoringVisibility
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                CustomTopBar(R.string.home_screen_top_bar_title)
            },
            content = { innerPadding ->
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(120.dp),
                    content = {
                        items(state.breedUiList) { breedUi ->
                            BreedItem(breedUi, onEvent)
                        }
                    },
                    modifier = Modifier.padding(innerPadding)
                )
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
