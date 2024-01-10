package org.care.packie.ui.component.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.care.packie.ui.theme.PackieDesignSystem

@Composable
fun PackieButton(
    modifier: Modifier = Modifier,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = PackieDesignSystem.colors.buttonWhite,
        contentColor = PackieDesignSystem.colors.black
    ),
    shape: Shape = RoundedCornerShape(8.dp),
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) = Button(
    modifier = modifier,
    colors = buttonColors,
    shape = shape,
    onClick = onClick,
    contentPadding = PaddingValues(vertical = 16.dp)
) {
    ProvideTextStyle(value = PackieDesignSystem.typography.subTitle) {
        content()
    }
}

@Preview
@Composable
fun PackieButtonPreview() {
    PackieButton(
        onClick = {}
    ) {
        Text(text = "hello")
    }
}
