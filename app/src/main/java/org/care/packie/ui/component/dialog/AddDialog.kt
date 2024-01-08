package org.care.packie.ui.component.dialog

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import org.care.packie.R
import org.care.packie.ui.AddDialogType
import org.care.packie.ui.theme.PackieDesignSystem
import org.care.packie.ui.theme.PackieTheme

private const val MAX_LENGTH = 7

@Composable
fun AddDialog(
    type: AddDialogType,
    onConfirmation: (String) -> Unit,
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }
    var isDialogOpen by remember { mutableStateOf(true) }

    if (isDialogOpen) {
        Box(
            modifier = Modifier
                .fillMaxWidth(fraction = 0.8f)
                .clip(RoundedCornerShape(size = 8.dp))
                .background(color = PackieDesignSystem.colors.white)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center)
            ) {
                AddDialogTitle(type)
                AddDialogTextField(
                    textFieldValue = textFieldValue,
                    onValueChange = { newValue -> textFieldValue = newValue },
                    onConfirmation = { confirmedValue ->
                        onConfirmation(confirmedValue)
                        isDialogOpen = false
                    }
                )
                AddDialogActionButtons(
                    textFieldValue = textFieldValue,
                    onDismiss = { isDialogOpen = false },
                    onConfirmation = onConfirmation
                )
            }
        }
    }
}

@Composable
fun AddDialogTitle(
    type: AddDialogType
) {
    Text(
        text = type.title,
        style = PackieDesignSystem.typography.title
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddDialogTextField(
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
                        text = stringResource(id = R.string.add_dialog_max_length_hint),
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

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = 6.dp)
            .height(1.dp)
            .background(PackieDesignSystem.colors.grayContent)
    )
}

@Composable
fun AddDialogActionButtons(
    textFieldValue: TextFieldValue,
    onDismiss: () -> Unit,
    onConfirmation: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        DismissButton(
            onDismiss = onDismiss
        )
        ConfirmButton(
            textFieldValue = textFieldValue,
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
            text = stringResource(id = R.string.add_dialog_dismiss),
            style = PackieDesignSystem.typography.subTitle,
            color = PackieDesignSystem.colors.grayCancel,
            fontWeight = FontWeight.Bold
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ConfirmButton(
    textFieldValue: TextFieldValue,
    onConfirmation: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextButton(
        onClick = {
            onConfirmation(textFieldValue.text)
            keyboardController?.hide()
        }
    ) {
        Text(
            text = stringResource(id = R.string.add_dialog_confirm),
            style = PackieDesignSystem.typography.subTitle,
            color = PackieDesignSystem.colors.purple,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(widthDp = 360)
@Composable
fun AddDialogPreview() {
    PackieTheme {
        AddDialog(
            type = AddDialogType.PACKING_CATEGORY,
            onConfirmation = { textValue ->
                Log.d("asdf", textValue)
            }
        )
    }
}
