package org.care.packie.ui.component.stuff

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import org.care.packie.utils.ui.bounceClickable

@Composable
fun ToggleableStuff(
    modifier: Modifier = Modifier,
    isChecked: Boolean = false,
    onToggle: (String)-> Unit = {},
    itemName: String
) {
    var isCheck by remember { mutableStateOf(isChecked) }

    StuffRow(
        modifier = modifier
            .bounceClickable {
                isCheck = !isCheck
                onToggle(itemName)
            }
            .clip(RoundedCornerShape(8.dp))
            .background(
                color = PackieDesignSystem.colors.backgroundBlackAlpha50,
            )
            .padding(
                horizontal = 10.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedContent(targetState = isCheck, label = "") {
            CheckIconOf(isChecked = it)
        }
        Spacer(modifier = Modifier.size(4.dp))
        StuffLabel(label = itemName)
    }
}

@Composable
fun CheckIconOf(isChecked: Boolean) = if (isChecked) {
    Icon(
        painter = painterResource(id = R.drawable.check_circle_36),
        contentDescription = null,
        tint = PackieDesignSystem.colors.greenCheck
    )
} else {
    Icon(
        painter = painterResource(id = R.drawable.check_circle_outline_36),
        contentDescription = null,
        tint = PackieDesignSystem.colors.white
    )
}

@Preview(showBackground = true)
@Composable
fun StuffToggleItemEnablePreview() {
    ToggleableStuff(itemName = "휴대폰", isChecked = true)
}

@Preview(showBackground = true)
@Composable
fun StuffToggleItemDisablePreview() {
    ToggleableStuff(itemName = "hello", isChecked = false)
}