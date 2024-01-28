package org.care.packie.feature

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

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

