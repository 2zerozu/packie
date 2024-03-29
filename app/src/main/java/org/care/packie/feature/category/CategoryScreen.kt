package org.care.packie.feature.category

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import org.care.packie.R
import org.care.packie.ui.DoneDialogType
import org.care.packie.ui.TextFieldDialogType
import org.care.packie.ui.component.ads.BannersAds
import org.care.packie.ui.component.category.Category
import org.care.packie.ui.component.category.SettingPopupMenu
import org.care.packie.ui.component.common.PackieButton
import org.care.packie.ui.component.dialog.DoneDialog
import org.care.packie.ui.component.dialog.TextFieldDialog
import org.care.packie.ui.theme.PackieDesignSystem
import org.care.packie.ui.theme.PackieTheme

// private const val MIN_CATEGORY_SIZE = 4
private const val MIN_SPACER_SIZE = 4
private const val MAX_SPACER_SIZE = 70

@Composable
fun CategoryScreen(
    categories: Set<String>,
    onClickAddCategory: (String) -> Unit = {},
    onClickEditCategory: (String, String) -> Unit = { _, _ -> },
    onClickDeleteCategory: (String) -> Unit = {},
    onClickCategory: (String) -> Unit = {},
    onClickPrivacyPolicy: () -> Unit = {},
    onClickTerms: () -> Unit = {},
    onClickContactUs: () -> Unit = {},
    onClickDeveloperInfo: () -> Unit = {}
) {
    val state = rememberCollapsingToolbarScaffoldState()
    //val isCollapseEnabled = categories.size >= MIN_CATEGORY_SIZE
    val toolbarStateProgress = state.toolbarState.progress
    var categoryName by remember { mutableStateOf("") }
    var dialogType by remember { mutableStateOf(TextFieldDialogType.ADD_CATEGORY) }
    var isTextFieldDialogOpen by remember { mutableStateOf(false) }
    var isDoneDialogOpen by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column {
        Box(
            modifier = Modifier
                .padding(top = 8.dp, end = 8.dp)
                .align(Alignment.End)
        ) {
            SettingPopupMenu(
                onClickPrivacyPolicy = onClickPrivacyPolicy,
                onClickTerms = onClickTerms,
                onClickContactUs = onClickContactUs,
                onClickDeveloperInfo = onClickDeveloperInfo
            )
        }
        CollapsingToolbarScaffold(
            modifier = Modifier.weight(1f),
            state = state,
            //enabled = isCollapseEnabled,
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            toolbar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
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
            if (categories.isEmpty()) {
                Text(
                    modifier = Modifier
                        .padding(top = 128.dp)
                        .fillMaxWidth(),
                    text = "[추가하기]를 눌러 카테고리를 추가해 주세요!",
                    style = PackieDesignSystem.typography.subTitle,
                    color = PackieDesignSystem.colors.white,
                    textAlign = TextAlign.Center
                )
            } else {
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
                            },
                            onClickCategory = { onClickCategory(content) }
                        )
                    }
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
        Spacer(modifier = Modifier.size(4.dp))
        BannersAds(
            modifier = Modifier.fillMaxWidth()
            .height(50.dp)
        )
    }

    AnimatedVisibility(
        visible = isTextFieldDialogOpen,
        enter = fadeIn()
    ) {
        TextFieldDialog(
            content = categoryName,
            type = dialogType,
            onConfirmation = {
                if (categories.contains(it)) {
                    Toast.makeText(context, "이미 추가된 카테고리예요", Toast.LENGTH_SHORT).show()
                } else {
                    when (dialogType) {
                        TextFieldDialogType.ADD_CATEGORY -> onClickAddCategory(it)
                        else -> onClickEditCategory(categoryName, it)
                    }
                    isTextFieldDialogOpen = false
                    categoryName = ""
                }
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
        CategoryScreen(setOf())
    }
}
