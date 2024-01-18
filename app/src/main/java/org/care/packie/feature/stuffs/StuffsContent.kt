package org.care.packie.feature.stuffs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.care.packie.ui.component.stuff.MutableStuffsGrid
import org.care.packie.ui.component.stuff.StuffsGrid
import org.care.packie.ui.theme.PackieTheme
import org.care.packie.utils.ui.CrossfadeToggle

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
    Box(
        modifier = modifier
    ) {
        CrossfadeToggle(
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

}

@Preview
@Composable
fun StuffsContentPreview() {
    PackieTheme {
        Column {
            StuffsScreenTopBar(
                category = "출근",
                isEditMode = false
            )
            StuffsContent(
                modifier = Modifier.weight(1f),
                isEditMode = false,
                stuffs = (1..100).associate { "item${it}" to false }
            )
            StuffsStickyBottom(isEditMode = false)
        }
    }
    
}