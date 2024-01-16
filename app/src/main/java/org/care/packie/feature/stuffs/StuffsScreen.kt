package org.care.packie.feature.stuffs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.care.packie.StuffsPreviewProvider
import org.care.packie.ui.component.stuff.rememberEditModeState
import org.care.packie.ui.theme.PackieTheme
import org.care.packie.utils.ui.scroll.PackieTopBarScrollBehavior
import org.care.packie.utils.ui.scroll.rememberPackieTopBarScrollState

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
    val stuffs = remember { mutableStateMapOf<String, Boolean>().apply { putAll(defaultStuffs) } }

    val editMode = rememberEditModeState()
    val topBarScrollState = rememberPackieTopBarScrollState(
        maxHeight = StuffsTopBarSpacerToken.maxHeight,
        minHeight = StuffsTopBarSpacerToken.minHeight
    )
    val topBarScrollBehavior = PackieTopBarScrollBehavior.rememberExitUntilCollapsedScrollState(
        topBarScrollState = topBarScrollState
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .nestedScroll(topBarScrollBehavior),
    ) {
        StuffsScreenTopBar(
            category = category,
            isEditMode = editMode.isEditMode,
            onCategoryClick = {},
            onBackClick = {},
            state = topBarScrollState
        )
        StuffsContent(
            modifier = Modifier.weight(1f),
            isEditMode = editMode.isEditMode,
            stuffs = stuffs,
            onAdd = {},
            onRemove = {},
            onToggle = {}
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

@Preview
@Composable
fun StuffScreenPreview() {
    PackieTheme {
        StuffsScreen(
            category = "출근",
            defaultStuffs = StuffsPreviewProvider.mockStuffs
        )
    }
}
