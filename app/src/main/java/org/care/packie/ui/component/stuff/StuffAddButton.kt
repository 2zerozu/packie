package org.care.packie.ui.component.stuff

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.care.packie.R
import org.care.packie.ui.theme.PackieDesignSystem

@Composable
fun StuffAddButton(
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit = {}
) {
    StuffRow(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onAddClick() }
            .clip(RoundedCornerShape(8.dp))
            .background(color = PackieDesignSystem.colors.backgroundBlackAlpha50)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        StuffLabel(label = stringResource(id = R.string.stuff_item_add_button_text))
        Icon(
            painter = painterResource(id = R.drawable.add_24),
            contentDescription = null,
            tint = PackieDesignSystem.colors.white,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StuffAddButtonPreview() {
    StuffAddButton()
}