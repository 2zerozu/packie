package org.care.packie.feature.packingCategory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import org.care.packie.R
import org.care.packie.ui.component.common.PackieButton
import org.care.packie.ui.component.packingCategory.PackingCategory
import org.care.packie.ui.theme.PackieDesignSystem
import org.care.packie.ui.theme.PackieTheme

@Composable
fun PackingScreen(
    categories: List<String>
) {
    val state = rememberCollapsingToolbarScaffoldState()

    val contentFontSize = PackieDesignSystem.typography.content.fontSize.value
    val titleFontSize = PackieDesignSystem.typography.title.fontSize.value
    val toolbarStateProgress = state.toolbarState.progress

    val fontSize =
        (contentFontSize + (titleFontSize - contentFontSize) * toolbarStateProgress)

    Column {
        CollapsingToolbarScaffold(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            state = state,
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            toolbar = {

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
        Spacer(modifier = Modifier.size(4.dp))
        PackieButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.packing_category_button))
        }
        Spacer(modifier = Modifier.size(44.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PackingScreenPreview() {
    PackieTheme {
        PackingScreen(listOf("출근", "놀러갈 때", "여행", "장보러 갈 때", "출근", "놀러갈 때", "여행", "장보러 갈 때"))
    }
}

//Spacer(modifier = Modifier.size(100.dp))
//PackieButton(
//onClick = { /*TODO*/ },
//modifier = Modifier
//.fillMaxWidth()
//.padding(horizontal = 16.dp)
//) {
//    Text(text = stringResource(id = R.string.packing_category_button))
//}
//Spacer(modifier = Modifier.size(44.dp))