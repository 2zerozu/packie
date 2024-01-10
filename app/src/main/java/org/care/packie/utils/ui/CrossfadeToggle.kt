package org.care.packie.utils.ui

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.care.packie.R

@Composable
fun CrossfadeToggle(
    modifier: Modifier = Modifier,
    isEnable: Boolean,
    label: String = stringResource(id = R.string.cross_fade_animation),
    enableComposable: @Composable () -> Unit,
    disableComposable: @Composable () -> Unit
) {
    Crossfade(
        modifier = Modifier,
        targetState = isEnable,
        label = label
    ) {
        if (it) {
            enableComposable()
        } else {
            disableComposable()
        }
    }

}