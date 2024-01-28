package org.care.packie.feature.stuffs

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.care.packie.ui.theme.PackieTheme
import org.care.packie.utils.ui.scroll.PackieTopBarScrollBehavior
import org.care.packie.utils.ui.scroll.rememberPackieTopBarState

@Composable
fun StuffsScreen(
    category: String,
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    isEmpty: Boolean = false,
    isEditMode: Boolean = false,
    currentStuffs: Map<String, Boolean>,
    onClickToggle: (String) -> Unit = {},
    onClickRemove: (String) -> Unit = {},
    onClickAdd: () -> Unit = {},
    onClickUpdate: (String) -> Unit = {},
    enableEditMode: () -> Unit = {},
    disableEditMode: () -> Unit = {},
    onCategoryClick: () -> Unit = {},
) {
    val topBarScrollState = rememberPackieTopBarState(
        maxHeight = StuffsTopBarSpacerToken.maxHeight,
        minHeight = StuffsTopBarSpacerToken.minHeight
    )
    val lazyGridState = rememberLazyGridState()
    val topBarScrollBehavior = PackieTopBarScrollBehavior.rememberExitUntilCollapsedScrollState(
        topBarScrollState = topBarScrollState,
        scrollableState = lazyGridState
    )
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .nestedScroll(topBarScrollBehavior),
        containerColor = Color.Transparent,
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        topBar = {
            StuffsScreenTopBar(
                category = category,
                isEditMode = isEditMode,
                onCategoryClick = onCategoryClick,
                onBackClick = { disableEditMode() },
                state = topBarScrollState
            )
        },
        bottomBar = {
            StuffsStickyBottom(
                isEditMode = isEditMode,
                isEmpty = isEmpty,
                onClickEdit = enableEditMode,
                onClickUpdate = {
                    onClickUpdate(category)
                },
            )
        }
    ) {
        StuffsContent(
            modifier = Modifier.padding(it)
                .fillMaxSize(),
            isEditMode = isEditMode,
            stuffs = currentStuffs,
            onAdd = onClickAdd,
            onRemove = onClickRemove,
            onToggle = onClickToggle,
            state = lazyGridState
        )
    }
}

@Preview
@Composable
fun StuffScreenPreview() {
    PackieTheme {
        StuffsScreen(
            category = "출근",
            //currentStuffs = StuffsPreviewProvider.mockStuffs
            currentStuffs = emptyMap()
        )
    }
}
