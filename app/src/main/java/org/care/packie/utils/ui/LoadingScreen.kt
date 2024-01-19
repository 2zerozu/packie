package org.care.packie.utils.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.care.packie.ui.theme.PackieDesignSystem
import org.care.packie.ui.theme.PackieTheme

@Composable
fun LoadingScreen(
    visible: Boolean = false,
    onDismissRequest: () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            CircularProgressIndicator(
                color = PackieDesignSystem.colors.purple
            )
        }
    }

}

@Preview
@Composable
fun LoadingScreenPreview() {
    PackieTheme {
        LoadingScreen(onDismissRequest = {})
    }
}