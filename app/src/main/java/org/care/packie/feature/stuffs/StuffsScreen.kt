package org.care.packie.feature.stuffs

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.CollapsingToolbarScaffoldState
import me.onebone.toolbar.CollapsingToolbarState
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import org.care.packie.R
import org.care.packie.StuffsPreviewProvider
import org.care.packie.ui.component.common.PackieButton
import org.care.packie.ui.component.stuff.MutableStuffsGrid
import org.care.packie.ui.component.stuff.MutableStuffsTopBarIconButton
import org.care.packie.ui.component.stuff.StuffsGrid
import org.care.packie.ui.component.stuff.StuffsTopBarIconButton
import org.care.packie.ui.component.stuff.ToggleableStuff
import org.care.packie.ui.component.stuff.rememberEditModeState
import org.care.packie.ui.theme.PackieDesignSystem
import org.care.packie.ui.theme.PackieTheme
import org.care.packie.utils.ui.CrossfadeToggle

private const val MIN_SPACER_SIZE = 4
private const val MAX_SPACER_SIZE = 110

@Composable
fun StuffsScreen(
    category: String,
    defaultStuffs: Map<String, Boolean>,
    onToggle: (Boolean) -> Unit = {},
    onRemove: (String) -> Unit = {},
    onAdd: () -> Unit = {},
) {
    val stuffs = remember { mutableStateMapOf<String, Boolean>().apply { putAll(defaultStuffs) }  }

    val editMode = rememberEditModeState()

    val collapsingToolbarScaffoldState = rememberCollapsingToolbarScaffoldState()
    Log.d("Test", collapsingToolbarScaffoldState.toolbarState.progress.toString())
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp),
    ) {
        CollapsingToolbarScaffold(
            modifier = Modifier.weight(1f),
            state = collapsingToolbarScaffoldState,
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            toolbar = {
                StuffsScreenTopBar(
                    category = category,
                    isEditMode = editMode.isEditMode,
                    onCategoryClick = {},
                    onBackClick = { editMode.disableEditMode() },
                    state = collapsingToolbarScaffoldState
                )
            }
        ) {
            StuffsContent(
                isEditMode = editMode.isEditMode,
                stuffs = stuffs,
                onAdd = {},
                onRemove = {},
                onToggle = {}
            )
        }
        Spacer(
            modifier = Modifier
                .size(
                    (MIN_SPACER_SIZE + (MAX_SPACER_SIZE - MIN_SPACER_SIZE) * collapsingToolbarScaffoldState.toolbarState.progress).dp
                )
        )
        StuffsStickyBottom(
            isEditMode = editMode.isEditMode,
            onClickEdit = {
                editMode.enableEditMode()
            },
            onClickUpdate = {
                editMode.disableEditMode()
            },
        )
    }
}

@Composable
fun StuffsScreenTopBar(
    category: String,
    isEditMode: Boolean,
    onCategoryClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    state: CollapsingToolbarScaffoldState
) {
    val topBarMaxHeight = 130.dp
    val topBarMinHeight = 40.dp
    val currentTopBarHeight = lerp(
        start = topBarMinHeight,
        stop = topBarMaxHeight,
        fraction = state.toolbarState.progress
    )
    val currentFontSize = lerp(
        start = PackieDesignSystem.typography.content.fontSize,
        stop = PackieDesignSystem.typography.title.fontSize,
        fraction = state.toolbarState.progress
    )
    Log.d("TAG", "${state.toolbarState.progress}")
    Box(
        modifier = Modifier
            .padding(
                top = 16.dp,
                bottom = 10.dp
            )
            .height(currentTopBarHeight)
    ) {
        CrossfadeToggle(
            isEnable = isEditMode,
            enableComposable = {
                MutableStuffsTopBarIconButton(
                    onBackClick = onBackClick
                )
            },
            disableComposable = {
                StuffsTopBarIconButton(
                    onCategoryClick = onCategoryClick
                )
            }
        )
        StuffsTitle(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            category = category,
            currentFontSize = currentFontSize
        )
    }

}

@Composable
fun StuffsTitle(
    modifier: Modifier = Modifier,
    category: String,
    currentFontSize: TextUnit
) {
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.stuffs_content_text, category),
        style = PackieDesignSystem.typography.title,
        color = PackieDesignSystem.colors.white,
        textAlign = TextAlign.Center,
        fontSize = currentFontSize
    )
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
) {
    CrossfadeToggle(
        modifier = modifier,
        isEnable = isEditMode,
        enableComposable = {
            MutableStuffsGrid(
                state = state,
                stuffs = stuffs,
                onAdd = onAdd,
                onRemove = onRemove
            )
        },
        disableComposable = {
            StuffsGrid(
                state = state,
                stuffs = stuffs,
                onToggle = onToggle
            )
        }
    )

}

@Composable
fun StuffsStickyBottom(
    modifier: Modifier = Modifier,
    isEditMode: Boolean,
    onClickEdit: () -> Unit = {},
    onClickUpdate: () -> Unit = {}
) = Column(
    modifier = modifier
) {
    PackieButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = if (isEditMode) onClickUpdate else onClickEdit
    ) {
        Text(
            text = if (isEditMode) {
                stringResource(id = R.string.stuffs_update)
            } else {
                stringResource(id = R.string.stuffs_edit)
            }
        )
    }
    Spacer(modifier = Modifier.size(44.dp))
}

@Preview
@Composable
fun StuffScreenPreview() {
    PackieTheme {
        StuffsScreen(
            category = "출근",
            defaultStuffs = (1..100).associate { "item${it}" to false }
        )
    }
}
