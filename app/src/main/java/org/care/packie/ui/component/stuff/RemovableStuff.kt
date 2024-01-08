package org.care.packie.ui.component.stuff

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.care.packie.R
import org.care.packie.ui.component.common.PackieIconButton
import org.care.packie.ui.theme.PackieDesignSystem

@Composable
fun RemovableStuffItem(
    modifier: Modifier = Modifier,
    content: String,
    onRemoveClick: (String) -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(
                color = PackieDesignSystem.colors.backgroundBlackAlpha50,
            )
            .padding(
                start = 16.dp, end = 10.dp, top = 16.dp, bottom = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        StuffLabel(
            label = content
        )
        PackieIconButton(
            onClick = {
                onRemoveClick(content)
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.close_24),
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 159)
@Composable
fun PreviewRemovableStuffItem() {
    Column {
        RemovableStuffItem(content = "휴대폰")
        RemovableStuffItem(content = "일이삼사오육칠팔구십일")
        RemovableStuffItem(content = "휴대폰")
    }
}