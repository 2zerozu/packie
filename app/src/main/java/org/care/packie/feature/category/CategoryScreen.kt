package org.care.packie.feature.category

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import org.care.packie.R
import org.care.packie.ui.DoneDialogType
import org.care.packie.ui.TextFieldDialogType
import org.care.packie.ui.component.category.Category
import org.care.packie.ui.component.common.PackieButton
import org.care.packie.ui.component.dialog.DoneDialog
import org.care.packie.ui.component.dialog.TextFieldDialog
import org.care.packie.ui.theme.PackieDesignSystem
import org.care.packie.ui.theme.PackieTheme

private const val MIN_CATEGORY_SIZE = 5
private const val MIN_SPACER_SIZE = 4
private const val MAX_SPACER_SIZE = 80

@Composable
fun CategoryScreen(
    viewModel: CategoryViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        viewModel.getCategories()
    }

    val categories by viewModel.categories.collectAsState()

    PackieTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            CategoryScreen(
                categories = categories,
                onClickAddCategory = { viewModel.addCategory(it) },
                onClickEditCategory = { old, new ->
                    viewModel.editCategory(old, new)
                },
                onClickDeleteCategory = { viewModel.deleteCategory(it) }
            )
        }
    }
}

@Composable
fun CategoryScreen(
    categories: Set<String>,
    onClickAddCategory: (String) -> Unit,
    onClickEditCategory: (String, String) -> Unit,
    onClickDeleteCategory: (String) -> Unit
) {
    val state = rememberCollapsingToolbarScaffoldState()
    val isCollapseEnabled = categories.size >= MIN_CATEGORY_SIZE
    val toolbarStateProgress = state.toolbarState.progress
    var categoryName by remember { mutableStateOf("") }
    var dialogType by remember { mutableStateOf(TextFieldDialogType.ADD_CATEGORY) }
    var isTextFieldDialogOpen by remember { mutableStateOf(false) }
    var isDoneDialogOpen by remember { mutableStateOf(false) }

    Column {
        CollapsingToolbarScaffold(
            modifier = Modifier.weight(1f),
            state = state,
            enabled = isCollapseEnabled,
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            toolbar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .pin()
                )
                Text(
                    text = stringResource(id = R.string.packing_category_title),
                    style = PackieDesignSystem.typography.title,
                    color = PackieDesignSystem.colors.white,
                    modifier = Modifier.road(Alignment.TopCenter, Alignment.Center),
                    fontSize = lerp(
                        start = PackieDesignSystem.typography.content.fontSize,
                        stop = PackieDesignSystem.typography.title.fontSize,
                        fraction = toolbarStateProgress
                    )
                )
            }
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                items(categories.size) { index ->
                    val content = categories.elementAt(index)
                    Category(
                        category = content,
                        onClickEdit = {
                            isTextFieldDialogOpen = true
                            dialogType = TextFieldDialogType.EDIT_CATEGORY
                            categoryName = content
                        },
                        onClickDelete = {
                            isDoneDialogOpen = true
                            categoryName = content
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size((MIN_SPACER_SIZE + (MAX_SPACER_SIZE - MIN_SPACER_SIZE) * toolbarStateProgress).dp))
        PackieButton(
            onClick = {
                isTextFieldDialogOpen = true; dialogType = TextFieldDialogType.ADD_CATEGORY
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.packing_category_button))
        }
        Spacer(modifier = Modifier.size(44.dp))
    }

    AnimatedVisibility(
        visible = isTextFieldDialogOpen,
        enter = fadeIn()
    ) {
        TextFieldDialog(
            content = categoryName,
            type = dialogType,
            onConfirmation = {
                if (dialogType == TextFieldDialogType.ADD_CATEGORY) onClickAddCategory(it)
                else onClickEditCategory(categoryName, it)
                isTextFieldDialogOpen = false
                categoryName = ""
            },
            onDismiss = {
                isTextFieldDialogOpen = false
                categoryName = ""
            }
        )
    }

    AnimatedVisibility(
        visible = isDoneDialogOpen,
        enter = fadeIn()
    ) {
        DoneDialog(
            type = DoneDialogType.DELETE,
            onConfirm = {
                onClickDeleteCategory(categoryName)
                isDoneDialogOpen = false
                categoryName = ""
            },
            onDismiss = { isDoneDialogOpen = false }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryScreenPreview() {
    PackieTheme {
        //CategoryScreen(setOf("출근", "퇴근"), {})
    }
}
