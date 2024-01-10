package org.care.packie.ui.component.category

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

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

        DropdownMenuItem(text = { Text(text = "수정하기") }, onClick = { onClickEdit() })
        DropdownMenuItem(text = { Text(text = "삭제하기") }, onClick = { onClickDelete() })
    }
}

@Preview
@Composable
fun CategoryPopupMenuPreview() {
    CategoryPopupMenu({}, {}, true, {})
}
