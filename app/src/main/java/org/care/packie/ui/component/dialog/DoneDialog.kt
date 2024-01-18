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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.care.packie.R
import org.care.packie.ui.DoneDialogType
import org.care.packie.ui.theme.PackieDesignSystem
import org.care.packie.ui.theme.PackieTheme

@Composable
fun DoneDialog(
    type: DoneDialogType,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(size = 8.dp))
                .background(color = PackieDesignSystem.colors.white)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center)
            ) {
                DoneDialogTitle(type)
                DoneDialogContent(type)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    DoneDialogConfirmButton(
                        onConfirmation = onConfirm
                    )
                }
            }
        }
    }
}

@Composable
fun DoneDialogTitle(
    type: DoneDialogType
) {
    Text(
        text = type.title,
        style = PackieDesignSystem.typography.title
    )
}

@Composable
fun DoneDialogContent(
    type: DoneDialogType
) {
    Text(
        text = type.content,
        color = PackieDesignSystem.colors.grayContent,
        style = PackieDesignSystem.typography.subTitle
    )
}

@Composable
fun DoneDialogConfirmButton(
    onConfirmation: () -> Unit
) {
    TextButton(
        onClick = { onConfirmation() }
    ) {
        Text(
            text = stringResource(id = R.string.done_dialog_confirm),
            style = PackieDesignSystem.typography.subTitle,
            fontWeight = FontWeight.Bold,
            color = PackieDesignSystem.colors.purple
        )
    }
}


@Preview(widthDp = 360)
@Composable
fun DoneDialogPreview() {
    PackieTheme {
        DoneDialog(DoneDialogType.COMPLETE, onConfirm = {}, {})
    }
}