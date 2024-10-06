package com.whisker.world.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.whisker.world.R

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
)

@Composable
fun getBottomNavigationItems() =
    listOf(
        BottomNavigationItem(
            title = stringResource(R.string.bottom_navigation_list),
            selectedIcon = Icons.Filled.Menu,
            unselectedIcon = Icons.Outlined.Menu,
            route = Routes.HOME

        ),
        BottomNavigationItem(
            title = stringResource(R.string.bottom_navigation_favourites),
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.Favorite,
            route = Routes.FAVOURITES
        )
    )