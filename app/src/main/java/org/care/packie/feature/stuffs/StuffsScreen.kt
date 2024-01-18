package org.care.packie.feature.stuffs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.care.packie.StuffsPreviewProvider
import org.care.packie.ui.AddDialogType
import org.care.packie.ui.component.dialog.AddDialog
import org.care.packie.ui.component.stuff.rememberEditModeState
import org.care.packie.ui.theme.PackieTheme
import org.care.packie.utils.ui.scroll.PackieTopBarScrollBehavior
import org.care.packie.utils.ui.scroll.rememberPackieTopBarState

private const val MIN_SPACER_SIZE = 4
private const val MAX_SPACER_SIZE = 110

@Composable
fun StuffsScreen(
    category: String,
    currentStuffs: Map<String, Boolean>,
    onClickToggle: (Boolean) -> Unit = {},
    onClickRemove: (String) -> Unit = {},
    onClickAdd: () -> Unit = {},
    onClickUpdate: (Map<String, Boolean>) -> Unit = {},
) {
    val editableStuffs =
        remember { mutableStateMapOf<String, Boolean>().apply { putAll(currentStuffs) } }
    val editMode = rememberEditModeState()
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    var isDialogOpen by remember { mutableStateOf(false) }

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
                isEditMode = editMode.isEditMode,
                onCategoryClick = {},
                onBackClick = {},
                state = topBarScrollState
            )
        },
        bottomBar = {
            StuffsStickyBottom(
                isEditMode = editMode.isEditMode,
                onClickEdit = {
                    editMode.enableEditMode()
                },
                onClickUpdate = {
                    editMode.disableEditMode()
                    onClickUpdate(editableStuffs)
                },
            )
        }
    ) {
        StuffsContent(
            modifier = Modifier.padding(it),
            isEditMode = editMode.isEditMode,
            stuffs = if (editMode.isEditMode) editableStuffs else currentStuffs,
            onAdd = {
                isDialogOpen = true
            },
            onRemove = {stuffName ->
                editableStuffs.remove(stuffName)
            },
            onToggle = onClickToggle,
            state = lazyGridState
        )
    }

    AnimatedVisibility(
        visible = isDialogOpen,
        enter = fadeIn()
    ) {
        AddDialog(
            type = AddDialogType.STUFF,
            onConfirmation = {
                if (editableStuffs.put(it, false) != null) {
                    scope.launch {
                        snackBarHostState.showSnackbar("${it}은 이미 추가된 물건이에요")
                    }
                }
                isDialogOpen = false
            },
            onDismiss = { isDialogOpen = false }
        )
    }
}

@Preview
@Composable
fun StuffScreenPreview() {
    PackieTheme {
        StuffsScreen(
            category = "출근",
            currentStuffs = StuffsPreviewProvider.mockStuffs
        )
    }
}
