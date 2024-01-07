package org.care.packie.ui.feature.packingCategory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.care.packie.R
import org.care.packie.ui.component.packingCategory.PackingCategory
import org.care.packie.ui.theme.PackieDesignSystem
import org.care.packie.ui.theme.PackieTheme

@Composable
fun PackingScreen(categories: List<String>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(42.dp),
        modifier = Modifier.padding(top = 60.dp)
    ) {
        Box(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(id = R.string.packing_category_title),
                style = PackieDesignSystem.typography.title,
                color = PackieDesignSystem.colors.white,
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            categories.forEach { category ->
                PackingCategory(category = category)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PackingScreenPreview() {
    PackieTheme {
        PackingScreen(listOf("출근", "놀러갈 때", "여행"))
    }
}