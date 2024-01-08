package org.care.packie.feature.packingCategory

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import org.care.packie.R
import org.care.packie.ui.AddDialogType
import org.care.packie.ui.component.common.PackieButton
import org.care.packie.ui.component.dialog.AddDialog
import org.care.packie.ui.component.packingCategory.PackingCategory
import org.care.packie.ui.theme.PackieDesignSystem
import org.care.packie.ui.theme.PackieTheme

private const val MIN_CATEGORY_SIZE = 5
private const val MIN_SPACER_SIZE = 4
private const val MAX_SPACER_SIZE = 80

@Composable
fun PackingScreen(
    categories: List<String>
) {
    val state = rememberCollapsingToolbarScaffoldState()
    val isCollapseEnabled = categories.size >= MIN_CATEGORY_SIZE
    val toolbarStateProgress = state.toolbarState.progress
    var isDialogOpen by remember { mutableStateOf(false) }

    Column {
        CollapsingToolbarScaffold(
            modifier = Modifier.weight(1f),
            state = state,
            enabled = isCollapseEnabled,
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            toolbar = {
                val contentFontSize = PackieDesignSystem.typography.content.fontSize.value
                val titleFontSize = PackieDesignSystem.typography.title.fontSize.value
                val fontSize =
                    (contentFontSize + (titleFontSize - contentFontSize) * toolbarStateProgress)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .pin()
                )
                Text(
                    text = stringResource(id = R.string.packing_category_title),
                    style = PackieDesignSystem.typography.title,
                    color = PackieDesignSystem.colors.white,
                    modifier = Modifier.road(Alignment.TopCenter, Alignment.Center),
                    fontSize = fontSize.sp
                )
            }
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                items(categories.size) { index ->
                    PackingCategory(category = categories[index])
                }
            }
        }
        Spacer(modifier = Modifier.size((MIN_SPACER_SIZE + (MAX_SPACER_SIZE - MIN_SPACER_SIZE) * toolbarStateProgress).dp))
        PackieButton(
            onClick = { isDialogOpen = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.packing_category_button))
        }
        Spacer(modifier = Modifier.size(44.dp))
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .then(if (isDialogOpen) Modifier.background(PackieDesignSystem.colors.backgroundBlackAlpha50) else Modifier)
            .then(if (isDialogOpen) Modifier.pointerInput(Unit) {} else Modifier),
        contentAlignment = Alignment.Center
    ) {

        if (isDialogOpen) {
            AddDialog(
                type = AddDialogType.PACKING_CATEGORY,
                onConfirmation = {
                    Log.d("asdf", "\'$it\'이 추가되었습니다")
                    isDialogOpen = false
                },
                onDismiss = { isDialogOpen = false }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PackingScreenPreview() {
    PackieTheme {
        PackingScreen(listOf("출근", "놀러갈 때", "여행", "출근", "놀러갈 때", "여행", "출근", "놀러갈 때"))
    }
}
