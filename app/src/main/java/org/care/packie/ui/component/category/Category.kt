package org.care.packie.ui.component.category

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.care.packie.R
import org.care.packie.ui.theme.PackieDesignSystem

@Composable
fun Category(
    category: String,
    onClickEdit: () -> Unit,
    onClickDelete: () -> Unit,
    onClickCategory: () -> Unit
) {
    var isPopupOpen by remember { mutableStateOf(false) }

    Row(
        Modifier
            .clip(RoundedCornerShape(size = 8.dp))
            .background(color = PackieDesignSystem.colors.backgroundBlackAlpha50)
            .fillMaxWidth()
            .clickable(onClick = onClickCategory)
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
            modifier = Modifier
                .padding(end = 8.dp)
                .clickable { isPopupOpen = true }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_category_more),
                contentDescription = null,
                tint = PackieDesignSystem.colors.white,
                modifier = Modifier.padding(16.dp)
            )

            CategoryPopupMenu(
                onClickEdit = {
                    isPopupOpen = false
                    onClickEdit()
                },
                onClickDelete = {
                    isPopupOpen = false
                    onClickDelete()
                },
                isPopupOpen = isPopupOpen,
                onDismissRequest = { isPopupOpen = false }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PackingCategoryPreview() {
    Category("출근", {}, {}, {})
}
