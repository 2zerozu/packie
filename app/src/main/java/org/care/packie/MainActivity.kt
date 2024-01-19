package org.care.packie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.care.packie.feature.category.CategoryScreen
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
    val navController = rememberNavController()
    PackieTheme {
        NavHost(
            navController = navController,
            // TODO: 추후 Category에도 구현하면 해당 부분 Category로 변경
            startDestination = PackieNavDestination.StuffsScreen.route
        ) {
            composable(PackieNavDestination.CategoryScreen.route) {
                CategoryScreen()
            }
            composable(PackieNavDestination.StuffsScreen.route) {
                StuffsScreenRoot()
            }
        }
    }
}

sealed class PackieNavDestination(
    val route: String
) {
    object CategoryScreen: PackieNavDestination(
        route = "category"
    )
    object StuffsScreen: PackieNavDestination(
        route = "stuffs"
    )
}
