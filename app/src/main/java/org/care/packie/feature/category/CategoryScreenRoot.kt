package org.care.packie.feature.category

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import org.care.packie.utils.ui.LoadingScreen

@Composable
fun CategoryScreenRoot(
    viewModel: CategoryViewModel = hiltViewModel(),
    navigateToStuff: (String) -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    var categories by remember { mutableStateOf(emptySet<String>()) }
    var isLoading by remember { mutableStateOf(false) }

    when (state) {
        is CategoryUiState.Loading -> {
            isLoading = true
            viewModel.getCategories()
        }

        is CategoryUiState.Success -> {
            isLoading = false
            categories = (state as CategoryUiState.Success).categories
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        CategoryScreen(
            categories = categories,
            onClickAddCategory = viewModel::addCategory,
            onClickEditCategory = viewModel::editCategory,
            onClickDeleteCategory = viewModel::removeCategory,
            onClickCategory = { navigateToStuff(it) },
        )

        LoadingScreen(
            visible = isLoading,
            onDismissRequest = { isLoading = false }
        )
    }
}
