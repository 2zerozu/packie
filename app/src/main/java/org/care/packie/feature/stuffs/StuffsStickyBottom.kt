package org.care.packie.feature.stuffs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.care.packie.R
import org.care.packie.ui.component.common.PackieButton

@Composable
fun StuffsStickyBottom(
    modifier: Modifier = Modifier,
    isEmpty: Boolean = false,
    isEditMode: Boolean,
    onClickEdit: () -> Unit = {},
    onClickUpdate: () -> Unit = {}
) = Column(
    modifier = modifier
) {
    Spacer(modifier = Modifier.size(4.dp))
    PackieButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = if (isEditMode) onClickUpdate else onClickEdit
    ) {
        Text(
            text = if (isEditMode) {
                stringResource(id = R.string.stuffs_update)
            } else {
                stringResource(
                    id = if (isEmpty) R.string.stuff_item_add_button_text else R.string.stuffs_edit
                )
            }
        )
    }
    Spacer(modifier = Modifier.size(44.dp))
}