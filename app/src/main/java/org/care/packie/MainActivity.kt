package org.care.packie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import dagger.hilt.android.AndroidEntryPoint
import org.care.packie.feature.stuffs.StuffsScreenRoot
import org.care.packie.ui.theme.PackieTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PackieApp()
        }
    }
}

@Composable
fun PackieApp() {
    PackieTheme {
        StuffsScreenRoot()
    }
}
