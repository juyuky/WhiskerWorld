package com.whisker.world.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.whisker.world.navigation.NavHost
import com.whisker.world.presentation.ui.theme.WhiskerWorldTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            WhiskerWorldTheme {
                val navController = rememberNavController()
                NavHost(navController)
            }
        }
    }
}
