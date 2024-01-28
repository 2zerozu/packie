package org.care.packie.feature

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
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






