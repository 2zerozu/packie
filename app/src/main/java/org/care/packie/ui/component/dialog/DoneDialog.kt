package org.care.packie.ui.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.care.packie.R
import org.care.packie.ui.theme.PackieDesignSystem
import org.care.packie.ui.theme.PackieTheme

@Composable
fun DoneDialog() {
    var openDialog by remember { mutableStateOf(true) }

    if (openDialog) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .clip(RoundedCornerShape(size = 8.dp))
                .background(color = PackieDesignSystem.colors.white)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(
                    text = stringResource(id = R.string.done_dialog_title),
                    style = PackieDesignSystem.typography.title
                )

                Text(
                    text = stringResource(id = R.string.done_dialog_content),
                    color = PackieDesignSystem.colors.grayContent,
                    style = PackieDesignSystem.typography.subTitle
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {

                    TextButton(
                        onClick = { openDialog = false }
                    ) {
                        Text(
                            text = stringResource(id = R.string.done_dialog_confirm),
                            style = PackieDesignSystem.typography.subTitle,
                            fontWeight = FontWeight.Bold,
                            color = PackieDesignSystem.colors.purple
                        )
                    }
                }
            }
        }
    }
}


@Preview(widthDp = 360)
@Composable
fun DoneDialogPreview() {
    PackieTheme {
        DoneDialog()
    }
}