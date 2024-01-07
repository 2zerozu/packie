package org.care.packie.ui.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.care.packie.R
import org.care.packie.ui.AddDialogType
import org.care.packie.ui.theme.PackieDesignSystem

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddDialog(
    type: AddDialogType,
    onConfirmation: (String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }
    val maxLength = 7
    var openDialog by remember { mutableStateOf(true) }

    if (openDialog) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .clip(RoundedCornerShape(size = 8.dp))
                .background(color = PackieDesignSystem.colors.white)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(
                    text = type.title,
                    style = PackieDesignSystem.typography.subTitle
                )

                Spacer(modifier = Modifier.height(4.dp))

                BasicTextField(
                    value = textFieldValue,
                    onValueChange = {
                        if (it.text.length <= maxLength) {
                            textFieldValue = it
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
                    textStyle = PackieDesignSystem.typography.content.copy(color = Color.Black),
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
                                    style = PackieDesignSystem.typography.content.copy(color = Color.Gray)
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
                        .background(PackieDesignSystem.colors.grayLine)
                )

                Text(
                    text = stringResource(
                        id = R.string.add_dialog_length,
                        textFieldValue.text.length,
                        maxLength
                    ),
                    modifier = Modifier
                        .align(Alignment.End)
                        .offset(y = 6.dp),
                    color = PackieDesignSystem.colors.grayLine
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {

                    TextButton(
                        onClick = {
                            openDialog = false
                            keyboardController?.hide()
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.add_dialog_dismiss),
                            style = PackieDesignSystem.typography.body,
                            color = PackieDesignSystem.colors.grayCancel
                        )
                    }

                    TextButton(
                        onClick = {
                            onConfirmation(textFieldValue.text)
                            keyboardController?.hide()
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.add_dialog_confirm),
                            style = PackieDesignSystem.typography.body,
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
fun AddDialogPreview() {
    AddDialog(
        type = AddDialogType.PACKING_CATEGORY,
        onConfirmation = { textValue ->
            Log.d("asdf", textValue)
        }
    )
}