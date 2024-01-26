package org.care.packie.feature.stuffs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import org.care.packie.ui.component.stuff.MutableStuffsGrid
import org.care.packie.ui.component.stuff.StuffsGrid
import org.care.packie.ui.theme.PackieDesignSystem
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
    onToggle: (String) -> Unit = {},
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
                if (stuffs.isEmpty()) {
                    Text(
                        modifier = modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        text = "수정하기를 눌러 준비물을 추가해주세요!",
                        style = PackieDesignSystem.typography.subTitle,
                        color = PackieDesignSystem.colors.white,
                        textAlign = TextAlign.Center
                    )
                } else {
                    StuffsGrid(
                        state = state,
                        stuffs = stuffs,
                        onToggle = onToggle
                    )
                }
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
                //stuffs = (1..100).associate { "item${it}" to false }
                stuffs = emptyMap()
            )
            StuffsStickyBottom(isEditMode = false)
        }
    }

}