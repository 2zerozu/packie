package org.care.packie.ui.component.stuff

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.care.packie.R
import org.care.packie.ui.component.common.PackieIconButton

@Composable
fun StuffsTopBar(
    onCategoryClick: () -> Unit = {},
) {
    StuffsTopBarRow {
        PackieIconButton(onClick = onCategoryClick) {
            Icon(
                painter = painterResource(id = R.drawable.menu),
                contentDescription = null
            )
        }
    }
}

@Composable
fun MutableStuffsTopBar(
    onBackClick: () -> Unit = {}
) {
    StuffsTopBarRow {
        PackieIconButton(onClick = onBackClick) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_back_24),
                contentDescription = null
            )
        }
    }

}

@Composable
fun StuffsTopBarRow(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) = Row(
    modifier = modifier.fillMaxWidth()
        .padding(
            start = 16.dp,
            end = 16.dp,
            top = 16.dp,
            bottom = 10.dp
        ),
    verticalAlignment = Alignment.CenterVertically,
    content = content
)

@Preview
@Composable
fun StuffsTopBarPreview() {
    StuffsTopBar()
}

@Preview
@Composable
fun MutableStuffsTopBarPreview() {
    MutableStuffsTopBar()
}