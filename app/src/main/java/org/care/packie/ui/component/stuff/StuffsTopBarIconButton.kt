package org.care.packie.ui.component.stuff

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
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
fun StuffsTopBarIconButton(
    onCategoryClick: () -> Unit = {},
) {
    PackieIconButton(onClick = onCategoryClick) {
        Icon(
            painter = painterResource(id = R.drawable.menu),
            contentDescription = null
        )
    }
}

@Composable
fun MutableStuffsTopBarIconButton(
    onBackClick: () -> Unit = {}
) {
    PackieIconButton(
        modifier = Modifier.size(34.dp),
        onClick = onBackClick
    ) {
        Icon(
            painter = painterResource(id = R.drawable.arrow_back_24),
            contentDescription = null
        )
    }
}

@Composable
fun StuffsTopBarRow(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    content = content
)

@Preview
@Composable
fun StuffsTopBarPreview() {
    StuffsTopBarIconButton()
}

@Preview
@Composable
fun MutableStuffsTopBarPreview() {
    MutableStuffsTopBarIconButton()
}