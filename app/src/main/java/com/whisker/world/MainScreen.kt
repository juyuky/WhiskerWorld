package com.whisker.world

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.whisker.world.ui.theme.WhiskerWorldTheme

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    WhiskerWorldTheme {
        MainScreen()
    }
}
