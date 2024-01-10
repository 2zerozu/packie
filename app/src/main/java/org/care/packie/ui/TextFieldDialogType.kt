package org.care.packie.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource
import org.care.packie.R

enum class TextFieldDialogType {
    ADD_CATEGORY,
    ADD_STUFF,
    EDIT_CATEGORY
    ;

    val title: String
        @Composable
        @ReadOnlyComposable
        get() = when (this) {
            ADD_CATEGORY -> stringResource(id = R.string.textField_dialog_add_category_title)
            ADD_STUFF -> stringResource(id = R.string.textField_dialog_add_stuff_title)
            EDIT_CATEGORY -> ""
        }
}
