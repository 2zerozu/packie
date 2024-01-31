package org.care.packie.feature

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
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
        var lastBackPressTime by remember { mutableLongStateOf(0L) }
        val context = LocalContext.current

        BackHandler {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastBackPressTime > 2000) {
                lastBackPressTime = currentTime
                Toast.makeText(context, "한 번 더 누르면 앱을 종료합니다.", Toast.LENGTH_SHORT).show()
            } else {
                navController.popBackStack()
            }
        }
        CategoryScreenRoot(
            navigateToStuff = { category ->
                navController.navigate(
                    route = PackingNavContract.Stuffs.getRoute(category)
                )
            },
            onClickPrivacyPolicy = {
                context.startActionViewActivity(
                    webUri = PackingNavContract.Url.privacyPolicyUri
                )
            },
            onClickContactUs = {
                context.startActionViewActivity(
                    webUri = PackingNavContract.Url.contactUsUri
                )
            },
            onClickTerms = {
                context.startActionViewActivity(
                    webUri = PackingNavContract.Url.termsUri
                )
            },
            onClickDeveloperInfo = {
                context.startActionViewActivity(
                    webUri = PackingNavContract.Url.developerInfoUri
                )
            }
        )
    }
}

private fun Context.startActionViewActivity(webUri: Uri) {
    Intent(Intent.ACTION_VIEW, webUri).also {
        startActivity(it)
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