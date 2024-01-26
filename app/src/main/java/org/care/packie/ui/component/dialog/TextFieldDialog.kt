package org.care.packie.ui.component.dialog

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.care.packie.R
import org.care.packie.ui.TextFieldDialogType
import org.care.packie.ui.theme.PackieDesignSystem
import org.care.packie.ui.theme.PackieTheme

private const val MAX_LENGTH = 7

@Composable
fun TextFieldDialog(
    content: String = "",
    type: TextFieldDialogType,
    onConfirmation: (String) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        var textFieldValue by remember { mutableStateOf(TextFieldValue(content)) }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(size = 12.dp))
                .background(color = PackieDesignSystem.colors.white)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
                .imePadding()
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center)
            ) {
                TextFieldDialogTitle(title = type.title)
                Spacer(modifier = Modifier.size(8.dp))
                TextFieldDialogTextField(
                    textFieldValue = textFieldValue,
                    onValueChange = { newValue -> textFieldValue = newValue },
                    onConfirmation = { confirmedValue ->
                        onConfirmation(confirmedValue)
                    }
                )
                TextFieldDialogTextFieldLength(
                    textFieldValue.text.length,
                    modifier = Modifier
                        .align(Alignment.End)
                        .offset(y = 6.dp),
                )
                Spacer(modifier = Modifier.size(12.dp))
                TextFieldDialogActionButtons(
                    confirm = type.confirm,
                    onDismiss = onDismiss,
                    onConfirmation = {
                        onConfirmation(textFieldValue.text)
                    }
                )
            }
        }
    }
}

@Composable
fun TextFieldDialogTitle(
    title: String
) {
    Text(
        text = title,
        style = PackieDesignSystem.typography.title
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextFieldDialogTextField(
    textFieldValue: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    onConfirmation: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    BasicTextField(
        value = textFieldValue,
        onValueChange = {
            if (it.text.length <= MAX_LENGTH) {
                onValueChange(it)
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                onConfirmation(textFieldValue.text)
                keyboardController?.hide()
            }
        ),
        cursorBrush = SolidColor(PackieDesignSystem.colors.purple),
        textStyle = PackieDesignSystem.typography.subTitle,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Transparent)
                    .padding(horizontal = 8.dp)
            ) {
                if (textFieldValue.text.isEmpty()) {
                    Text(
                        text = stringResource(id = R.string.textField_dialog_max_length_hint),
                        style = PackieDesignSystem.typography.subTitle,
                        color = PackieDesignSystem.colors.grayContent
                    )
                }
                innerTextField()
            }
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = 6.dp)
    )
    Spacer(modifier = Modifier.size(4.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = 6.dp)
            .height(1.dp)
            .background(PackieDesignSystem.colors.grayContent)
    )
}

@Composable
fun TextFieldDialogTextFieldLength(
    length: Int,
    modifier: Modifier
) {
    Text(
        text = stringResource(id = R.string.textField_dialog_length, length, MAX_LENGTH),
        modifier = modifier,
        color = PackieDesignSystem.colors.grayContent,
        style = PackieDesignSystem.typography.body
    )
}

@Composable
fun TextFieldDialogActionButtons(
    confirm: String,
    onDismiss: () -> Unit,
    onConfirmation: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        DismissButton(
            onDismiss = onDismiss
        )
        ConfirmButton(
            confirm = confirm,
            onConfirmation = onConfirmation
        )
    }
}

@Composable
fun DismissButton(
    onDismiss: () -> Unit
) {
    TextButton(
        onClick = {
            onDismiss()
        }
    ) {
        Text(
            text = stringResource(id = R.string.textField_dialog_dismiss),
            style = PackieDesignSystem.typography.subTitle,
            color = PackieDesignSystem.colors.grayCancel,
            fontWeight = FontWeight.Bold
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ConfirmButton(
    confirm: String,
    onConfirmation: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextButton(
        onClick = {
            onConfirmation()
            keyboardController?.hide()
        }
    ) {
        Text(
            text = confirm,
            style = PackieDesignSystem.typography.subTitle,
            color = PackieDesignSystem.colors.purple,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(widthDp = 360)
@Composable
fun TextFieldDialogPreview() {
    PackieTheme {
        TextFieldDialog(
            content = "아아아악",
            type = TextFieldDialogType.EDIT_CATEGORY,
            onConfirmation = { textValue ->
                Log.d("asdf", textValue)
            },
            onDismiss = {}
        )
    }
}
