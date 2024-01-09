package org.care.packie.feature.stuffs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import org.care.packie.R
import org.care.packie.StuffsPreviewProvider
import org.care.packie.ui.component.common.PackieButton
import org.care.packie.ui.component.stuff.MutableStuffsGrid
import org.care.packie.ui.component.stuff.MutableStuffsTopBar
import org.care.packie.ui.component.stuff.StuffsGrid
import org.care.packie.ui.component.stuff.StuffsTopBar
import org.care.packie.ui.theme.PackieDesignSystem
import org.care.packie.ui.theme.PackieTheme
import org.care.packie.utils.ui.CrossfadeToggle

@Composable
fun StuffsScreen(
    category: String,
    defaultStuffs: Map<String, Boolean>,
    onToggle: (Boolean) -> Unit = {},
    onRemove: (String) -> Unit = {},
    onAdd: () -> Unit = {},
) {
    val stuffs = remember { mutableStateMapOf<String, Boolean>().apply { putAll(defaultStuffs) } }

    var isEditMode by remember { mutableStateOf(false) }

    val lazyGridState = rememberLazyGridState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color.Transparent,
        topBar = {
            StuffsScreenTopBar(
                category = category,
                isEditMode = isEditMode,
                onCategoryClick = {},
                onBackClick = { isEditMode = false }
            )
        },
        bottomBar = {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Spacer(modifier = Modifier.size(4.dp))
                PackieButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        isEditMode = !isEditMode
                    }
                ) {
                    Text(
                        text = if (isEditMode) {
                            stringResource(id = R.string.stuffs_update)
                        } else {
                            stringResource(id = R.string.stuffs_edit)
                        }
                    )
                }
                Spacer(modifier = Modifier.size(68.dp))
            }
        }
    ) {
        StuffsContent(
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = it.calculateTopPadding(),
                bottom = it.calculateBottomPadding()
            ),
            isEditMode = isEditMode,
            stuffs = stuffs,
            state = lazyGridState,
            onAdd = {},
            onRemove = {},
            onToggle = {}
        )
    }
}

@Composable
fun StuffsScreenTopBar(
    category: String,
    isEditMode: Boolean,
    onCategoryClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    Column {
        CrossfadeToggle(
            isEnable = isEditMode,
            enableComposable = {
                MutableStuffsTopBar(
                    onBackClick = onBackClick
                )
            },
            disableComposable = {
                StuffsTopBar(
                    onCategoryClick = onCategoryClick
                )
            }
        )
        StuffsTitle(category)
    }
}

@Composable
fun StuffsTitle(
    category: String
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.stuffs_content_text, category),
        style = PackieDesignSystem.typography.title,
        color = PackieDesignSystem.colors.white,
        textAlign = TextAlign.Center,
    )
    Spacer(modifier = Modifier.size(20.dp))
}

@Composable
fun StuffsContent(
    modifier: Modifier = Modifier,
    state: LazyGridState = rememberLazyGridState(),
    isEditMode: Boolean,
    stuffs: Map<String, Boolean>,
    onAdd: () -> Unit = {},
    onRemove: (String) -> Unit = {},
    onToggle: (Boolean) -> Unit = {},
) = CrossfadeToggle(
    isEnable = isEditMode,
    enableComposable = {
        MutableStuffsGrid(
            modifier = modifier,
            state = state,
            stuffs = stuffs,
            onAdd = onAdd,
            onRemove = onRemove
        )
    },
    disableComposable = {
        StuffsGrid(
            modifier = modifier,
            state = state,
            stuffs = stuffs,
            onToggle = onToggle
        )
    }
)

@Preview
@Composable
fun StuffScreenPreview(
    @PreviewParameter(StuffsPreviewProvider::class) stuffs: Map<String, Boolean>
) {
    PackieTheme {
        StuffsScreen(
            category = "출근",
            defaultStuffs = stuffs
        )
    }
}
