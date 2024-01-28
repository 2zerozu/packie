package org.care.packie.feature

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import org.care.packie.PackieNavDestination
import org.care.packie.feature.category.CategoryScreenRoot
import org.care.packie.feature.stuffs.StuffsScreenRoot

@Composable
fun PackieNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PackingNavContract.ROUTE
    ) {
        packingGraph(navController = navController)
    }
}

private fun NavGraphBuilder.packingGraph(
    navController: NavController
) {
    navigation(
        route = PackingNavContract.ROUTE,
        startDestination = PackingNavContract.Category.ROUTE
    ) {
        composable(
            route = PackingNavContract.Category.ROUTE
        ) {
            CategoryScreenRoot(
                navigateToStuff = { category ->
                    navController.navigate(
                        route = PackingNavContract.Stuffs.getRoute(category)
                    )
                }
            )
        }

        composable(
            route = PackingNavContract.Stuffs.getRoute()
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

private object PackingNavContract {
    const val ROUTE = "packing"

    object Category {
        const val ROUTE = "category"
    }

    object Stuffs {
        const val CATEGORY_ARGUMENT = "category"
        private const val BASE_ROUTE = "stuffs"

        fun getRoute(category: String? = null): String {
            return category?.let {
                "$BASE_ROUTE/${it}"
            } ?: "$BASE_ROUTE/{$CATEGORY_ARGUMENT}"
        }
    }
}






