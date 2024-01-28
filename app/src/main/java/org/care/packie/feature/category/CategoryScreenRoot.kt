package org.care.packie.feature.category

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import org.care.packie.ui.theme.PackieTheme

@Composable
fun CategoryScreenRoot(
    viewModel: CategoryViewModel = hiltViewModel(),
    navigateToStuff: (String) -> Unit = {}
) {
    viewModel.getCategories()

    val categories by viewModel.categories.collectAsState()

    PackieTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            CategoryScreen(
                categories = categories,
                onClickAddCategory = { viewModel.addCategory(it) },
                onClickEditCategory = { old, new ->
                    viewModel.editCategory(old, new)
                },
                onClickDeleteCategory = { viewModel.removeCategory(it) },
                onClickCategory = { navigateToStuff(it) }
            )
        }
    }
}
