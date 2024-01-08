package org.care.packie.ui.component.stuff

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import org.care.packie.domain.StuffName
import org.care.packie.ui.theme.PackieDesignSystem

@Composable
fun StuffLabel(
    label: String
) = Text(
    text = StuffName(label).limitMaxLength(StuffName.LIMITE_LENGHT),
    style = PackieDesignSystem.typography.content,
    color = PackieDesignSystem.colors.white,
    maxLines = 1,
    overflow = TextOverflow.Ellipsis
)

@Preview(widthDp = 159)
@Composable
private fun StuffItemContentPreview() {
    Column {
        StuffLabel(label = "hello")
        StuffLabel(label = "일이삼사오육칠팔구")
    }
}
