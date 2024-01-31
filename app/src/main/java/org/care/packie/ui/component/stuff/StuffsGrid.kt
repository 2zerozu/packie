package org.care.packie.ui.component.stuff

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StuffsGrid(
    modifier: Modifier = Modifier,
    state: LazyGridState = rememberLazyGridState(),
    stuffs: Map<String, Boolean>,
    onToggle: (String) -> Unit = {}
) {
    LazyVerticalGrid(
        modifier = modifier,
        state = state,
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = stuffs.entries.toList(),
            key = { stuff -> stuff.key }
        ) {
            ToggleableStuff(
                itemName = it.key,
                isChecked = it.value,
                onToggle = onToggle
            )
        }
    }
}

@Composable
fun MutableStuffsGrid(
    modifier: Modifier = Modifier,
    state: LazyGridState = rememberLazyGridState(),
    stuffs: Map<String, Boolean>,
    onRemove: (String) -> Unit = {},
    onAdd: () -> Unit = {}
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        state = state
    ) {
        items(
            items = stuffs.entries.toList(),
            key = { stuff -> stuff.key }
        ) {
            RemovableStuffItem(
                content = it.key,
                onRemoveClick = onRemove
            )
        }
        item {
            StuffAddButton(
                onAddClick = onAdd
            )
        }
    }
}
