package org.care.packie.ui.component.packingCategory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.care.packie.R
import org.care.packie.ui.theme.PackieDesignSystem

@Composable
fun PackingCategory(
    category: String
) {
    Row(
        Modifier
            .clip(RoundedCornerShape(size = 8.dp))
            .background(color = PackieDesignSystem.colors.backgroundBlackAlpha50)
            .fillMaxWidth()
            .padding(vertical = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = category,
            style = PackieDesignSystem.typography.subTitle,
            color = PackieDesignSystem.colors.white,
            modifier = Modifier.padding(start = 32.dp)
        )
        Box(
            modifier = Modifier.padding(end = 8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_packing_recorder),
                contentDescription = null,
                tint = PackieDesignSystem.colors.white,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PackingCategoryPreview() {
    PackingCategory("출근")
}