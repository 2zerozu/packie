package org.care.packie.feature.stuffs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import org.care.packie.R
import org.care.packie.ui.component.stuff.MutableStuffsTopBarIconButton
import org.care.packie.ui.component.stuff.StuffsTopBarIconButton
import org.care.packie.ui.theme.PackieDesignSystem
import org.care.packie.utils.ui.CrossfadeToggle
import org.care.packie.utils.ui.scroll.PackieTopBarScrollState
import org.care.packie.utils.ui.scroll.rememberPackieTopBarState

object StuffsTopBarSpacerToken {
    val maxHeight = 131.dp
    val minHeight = 70.dp
}

@Composable
fun StuffsScreenTopBar(
    modifier: Modifier = Modifier,
    category: String,
    isEditMode: Boolean,
    onCategoryClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    state: PackieTopBarScrollState = rememberPackieTopBarState(
        maxHeight = StuffsTopBarSpacerToken.maxHeight,
        minHeight = StuffsTopBarSpacerToken.minHeight
    )
) {
    val titleSize = lerp(
        start = PackieDesignSystem.typography.title.fontSize,
        stop = PackieDesignSystem.typography.content.fontSize,
        fraction = state.progress
    )
    Box(modifier = modifier
        .height(state.height)
    ) {
        StuffsTopBarNavigationButton(
            isEditMode = isEditMode,
            onCategoryClick = onCategoryClick,
            onBackClick = onBackClick
        )
        StuffsTitle(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(top = 40.dp),
            category = category,
            currentFontSize = titleSize
        )
    }
}

@Composable
fun StuffsTopBarNavigationButton(
    modifier: Modifier = Modifier,
    isEditMode: Boolean,
    onCategoryClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
) {
    Box(
        modifier = modifier.padding(
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

@Preview
@Composable
fun StuffsScreenEnableEditModeMaxTopBarPreview() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StuffsScreenTopBar(
            category = "출근",
            isEditMode = true,
        )
    }
}