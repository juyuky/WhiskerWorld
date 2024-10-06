package com.whisker.world.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.whisker.world.R
import com.whisker.world.navigation.getBottomNavigationItems

@Composable
fun BaseScreen(
    currentRoute: String?,
    showError: Boolean,
    onNavigationBarItemClicked: () -> Unit,
    content: @Composable () -> Unit
) {
    val navigationItems = getBottomNavigationItems()
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
                    if (showError) {
                        ErrorScreen()
                    } else {
                        content()
                    }
                }
            },
            bottomBar = {
                NavigationBar {
                    navigationItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = currentRoute == item.route,
                            onClick = {
                                onNavigationBarItemClicked()
                            },
                            label = {
                                Text(text = item.title)
                            },
                            alwaysShowLabel = false,
                            icon = {
                                Icon(
                                    imageVector = if (currentRoute == item.route) {
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
        )
    }
}
