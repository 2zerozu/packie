package org.care.packie.feature

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.care.packie.feature.category.CategoryScreenRoot
import org.care.packie.feature.stuffs.StuffsScreenRoot

fun NavGraphBuilder.packingGraph(
    navController: NavController
) {
    navigation(
        route = PackingNavContract.ROUTE,
        startDestination = PackingNavContract.Category.ROUTE
    ) {
        categoryComposable(navController)
        stuffsComposable(navController)
    }
}

private fun NavGraphBuilder.categoryComposable(
    navController: NavController
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
}

private fun NavGraphBuilder.stuffsComposable(
    navController: NavController
) {
    composable(
        route = PackingNavContract.Stuffs.getRoute()
    ) { entry ->
        val category = entry.arguments
            ?.getString(PackingNavContract.Stuffs.CATEGORY_ARGUMENT)
            ?: throw IllegalArgumentException("category is required")
        StuffsScreenRoot(
            category = category,
            navigateToCategory = {
                navController.popBackStack()
            }
        )
    }
}