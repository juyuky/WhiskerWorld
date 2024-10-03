package com.whisker.world.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
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
import com.whisker.world.navigation.getBottomNavigationItems
import com.whisker.world.presentation.component.BreedItem
import com.whisker.world.presentation.ui.theme.WhiskerWorldTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
            bottomBar = {
                GetNavigationBar(onEvent)
            }
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(120.dp),
                content = {
                    items(state.breedUiList) { breedUi ->
                        BreedItem(breedUi)
                    }
                }
            )
        }
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
                    onEvent(HomeEvent.onNavigationBarItemClicked)
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
