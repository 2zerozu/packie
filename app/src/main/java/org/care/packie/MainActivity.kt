package org.care.packie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import dagger.hilt.android.AndroidEntryPoint
import org.care.packie.feature.category.CategoryScreenRoot
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
private fun PackieApp() {
    val navController = rememberNavController()
    PackieTheme {
        NavHost(
            navController = navController,
            // TODO: 추후 Category에도 구현하면 해당 부분 Category로 변경
            startDestination = "main"
        ) {
            packingGraph(navController)
        }
    }
}

private fun NavGraphBuilder.packingGraph(navController: NavController) {
    navigation(
        startDestination = PackieNavDestination.CategoryScreen.route,
        route = "main"
    ) {
        composable(PackieNavDestination.CategoryScreen.route) {
            CategoryScreenRoot(
                navigateToStuff = { category ->
                    navController.navigate("stuffs/$category")
                }
            )
        }
        composable(
            route = PackieNavDestination.StuffsScreen.route,
            arguments = listOf(
                navArgument(PackieNavDestination.StuffsScreen.categoryNavArgumentKey) {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            val category = entry.arguments
                ?.getString(PackieNavDestination.StuffsScreen.categoryNavArgumentKey)
                ?: throw IllegalArgumentException("category is required")
            StuffsScreenRoot(
                category = category,
                navigateToCategory = {
                    navController.popBackStack()
                }
            )
        }
    }
}

sealed class PackieNavDestination(
    val route: String
) {
    object CategoryScreen : PackieNavDestination(
        route = "category"
    )

    object StuffsScreen : PackieNavDestination(
        route = "stuffs/{category}"
    ) {
        const val categoryNavArgumentKey = "category"
    }
}
