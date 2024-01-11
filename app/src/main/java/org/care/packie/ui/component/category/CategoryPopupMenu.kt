package org.care.packie.ui.component.category

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.care.packie.R
import org.care.packie.ui.theme.PackieDesignSystem

@Composable
fun CategoryPopupMenu(
    onClickEdit: () -> Unit,
    onClickDelete: () -> Unit,
    isPopupOpen: Boolean,
    onDismissRequest: () -> Unit
) {
    DropdownMenu(
        expanded = isPopupOpen,
        onDismissRequest = onDismissRequest
    ) {
        DropdownMenuItem(
            text = {
                Text(
                    text = stringResource(id = R.string.popup_menu_edit),
                    style = PackieDesignSystem.typography.content
                )
            },
            onClick = { onClickEdit() }
        )
        DropdownMenuItem(
            text = {
                Text(
                    text = stringResource(id = R.string.popup_menu_delete),
                    style = PackieDesignSystem.typography.content
                )
            },
            onClick = { onClickDelete() }
        )
    }
}

@Preview
@Composable
fun CategoryPopupMenuPreview() {
    CategoryPopupMenu({}, {}, true, {})
}
