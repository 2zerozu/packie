package org.care.packie.ui.component.stuff

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.care.packie.ui.theme.PackieTheme

@Composable
fun StuffsGrid(
    modifier: Modifier = Modifier,
    stuffs: Map<String, Boolean>,
    onToggle: (Boolean) -> Unit = {}
) {
    LazyVerticalGrid(
        modifier = modifier,
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
    stuffs: Map<String, Boolean>,
    onRemove: (String)-> Unit = {},
    onAdd: () -> Unit = {}
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
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

@Preview
@Composable
fun StuffsGridPreview() {
    PackieTheme {
        StuffsGrid(
            stuffs = mapOf(
                "휴대폰" to false,
                "롤" to false,
                "하나둘셋넷" to false,
                "일이삼사오육칠팔구" to false,
                "어휴" to false,
                "에휴" to false,
                "오호" to false,
            )
        )
    }
}

@Preview
@Composable
fun MutableStuffsGridPreview() {
    PackieTheme {
        MutableStuffsGrid(
            stuffs = mapOf(
                "휴대폰" to false,
                "롤" to false,
                "하나둘셋넷" to false,
                "일이삼사오육칠팔구" to false,
                "어휴" to false,
                "에휴" to false,
                "오호" to false,
            )
        )
    }
}