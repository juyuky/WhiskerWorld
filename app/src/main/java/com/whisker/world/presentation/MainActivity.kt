package com.whisker.world.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.whisker.world.presentation.home.HomeScreen
import com.whisker.world.presentation.home.HomeViewModel
import com.whisker.world.presentation.ui.theme.WhiskerWorldTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            WhiskerWorldTheme {
                HomeScreen(state = viewModel.state.collectAsState().value) { }
            }
        }
    }
}
