package org.care.packie.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource
import org.care.packie.R

enum class AddDialogType {
    PACKING_CATEGORY,
    STUFF
    ;

    val title: String
        @Composable
        @ReadOnlyComposable
        get() = when (this) {
            PACKING_CATEGORY -> stringResource(id = R.string.add_dialog_packing_category_title)
            STUFF -> stringResource(id = R.string.add_dialog_stuff_title)
        }
}
