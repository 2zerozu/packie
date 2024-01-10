package org.care.packie.feature.stuffs

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import org.care.packie.R
import org.care.packie.StuffsPreviewProvider
import org.care.packie.ui.component.common.PackieButton
import org.care.packie.ui.component.stuff.MutableStuffsGrid
import org.care.packie.ui.component.stuff.MutableStuffsTopBarIconButton
import org.care.packie.ui.component.stuff.StuffsGrid
import org.care.packie.ui.component.stuff.StuffsTopBarIconButton
import org.care.packie.ui.component.stuff.rememberEditModeState
import org.care.packie.ui.theme.PackieDesignSystem
import org.care.packie.ui.theme.PackieTheme
import org.care.packie.utils.ui.CrossfadeToggle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StuffsScreen(
    category: String,
    defaultStuffs: Map<String, Boolean>,
    onToggle: (Boolean) -> Unit = {},
    onRemove: (String) -> Unit = {},
    onAdd: () -> Unit = {},
) {
    val stuffs = remember { mutableStateMapOf<String, Boolean>().apply { putAll(defaultStuffs) } }

    val editMode = rememberEditModeState()

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .fillMaxSize(),
        containerColor = Color.Transparent,
        topBar = {
            StuffsScreenTopBar(
                category = category,
                isEditMode = editMode.isEditMode,
                onCategoryClick = {},
                onBackClick = { editMode.disableEditMode() },
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            StuffsStickyBottom(
                modifier = Modifier.padding(horizontal = 16.dp),
                isEditMode = editMode.isEditMode,
                onClickEdit = {
                    editMode.enableEditMode()
                },
                onClickUpdate = {
                    editMode.disableEditMode()
                }
            )
        }
    ) {
        StuffsContent(
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = it.calculateTopPadding(),
                bottom = it.calculateBottomPadding()
            ),
            isEditMode = editMode.isEditMode,
            stuffs = stuffs,
            onAdd = {},
            onRemove = {},
            onToggle = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StuffsScreenTopBar(
    category: String,
    isEditMode: Boolean,
    onCategoryClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior
) {
    val maxSpaceSize = 20.dp
    val pinnedSpaceSize = 0.dp
    val maxSpacePx: Float
    val pinnedSpacePx: Float
    val maxFontSize = PackieDesignSystem.typography.title.fontSize
    val pinnedFontSize = PackieDesignSystem.typography.content.fontSize

    val currentSpaceSize =
        lerp(maxSpaceSize, pinnedSpaceSize, scrollBehavior.state.collapsedFraction)
    val currentFontSize = lerp(maxFontSize, pinnedFontSize, scrollBehavior.state.collapsedFraction)

    Log.d("size", currentSpaceSize.toString())
    Log.d("fontSize", currentFontSize.toString())

    LocalDensity.current.run {
        pinnedSpacePx = pinnedSpaceSize.toPx()
        maxSpacePx = maxSpaceSize.toPx()
    }

    SideEffect {
        if (scrollBehavior.state.heightOffsetLimit != pinnedSpacePx - maxSpacePx) {
            scrollBehavior.state.heightOffsetLimit = pinnedSpacePx - maxSpacePx
        }
    }

    Column {
        Row(
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = 10.dp
            )
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
        }
        StuffsTitle(
            category = category,
            currentFontSize = currentFontSize,
            currentSpaceSize = currentSpaceSize
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StuffsTitle(
    category: String,
    currentFontSize: TextUnit,
    currentSpaceSize: Dp
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.stuffs_content_text, category),
        style = PackieDesignSystem.typography.title,
        color = PackieDesignSystem.colors.white,
        textAlign = TextAlign.Center,
        fontSize = currentFontSize
    )
    Spacer(
        modifier = Modifier
            .size(currentSpaceSize)
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

@Composable
fun StuffsStickyBottom(
    modifier: Modifier = Modifier,
    isEditMode: Boolean,
    onClickEdit: () -> Unit = {},
    onClickUpdate: () -> Unit = {}
) = Column(
    modifier = modifier
) {
    Spacer(modifier = Modifier.size(4.dp))
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
    Spacer(modifier = Modifier.size(68.dp))
}

@Preview
@Composable
fun StuffScreenPreview(
    @PreviewParameter(StuffsPreviewProvider::class) stuffs: Map<String, Boolean> = StuffsPreviewProvider().values.first()
) {
    PackieTheme {
        StuffsScreen(
            category = "출근",
            defaultStuffs = stuffs
        )
    }
}
