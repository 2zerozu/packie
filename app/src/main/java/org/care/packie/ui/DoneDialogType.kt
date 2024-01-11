package org.care.packie.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource
import org.care.packie.R

enum class DoneDialogType {
    COMPLETE,
    DELETE,
    ;

    val title: String
        @Composable
        @ReadOnlyComposable
        get() = when (this) {
            COMPLETE -> stringResource(id = R.string.done_dialog_complete_title)
            DELETE -> stringResource(id = R.string.done_dialog_delete_title)
        }

    val content: String
        @Composable
        @ReadOnlyComposable
        get() = when (this) {
            COMPLETE -> stringResource(id = R.string.done_dialog_complete_content)
            DELETE -> stringResource(id = R.string.done_dialog_delete_content)
        }
}
