package org.care.packie.ui.component.stuff

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun rememberEditModeState(
    initialState: Boolean = false
): EditModeState = rememberSaveable(saver = EditModeState.Saver) {
    EditModeState(initialState)
}


@Stable
class EditModeState(
    initialState: Boolean
) {
    private var _isEditMode by mutableStateOf(initialState)

    val isEditMode: Boolean get() = _isEditMode

    fun enableEditMode() {
        _isEditMode = true
    }

    fun disableEditMode() {
        _isEditMode = false
    }

    companion object {
        val Saver: Saver<EditModeState, *> = run {
            val key = "EditMode"
            mapSaver(
                save = {
                    mapOf(key to it.isEditMode)
                },
                restore = {
                    EditModeState(
                        initialState = it[key] as Boolean
                    )
                }
            )
        }
    }
}