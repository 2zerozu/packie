package org.care.packie.utils.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput

enum class ButtonState {
    Pressed, Idle;
}

fun Modifier.bounceClickable(
    onClick: () -> Unit = {}
) = composed {
    var buttonState by remember { mutableStateOf(ButtonState.Idle) }
    val scaleState by animateFloatAsState(
        targetValue = if (buttonState == ButtonState.Pressed) 0.95f else 1f,
        label = ""
    )

    this
        .graphicsLayer {
            scaleX = scaleState
            scaleY = scaleState
        }
        .clickable(
            interactionSource = remember {
                MutableInteractionSource()
            },
            indication = null,
            onClick = onClick
        )
        .pointerInput(buttonState) {
            awaitPointerEventScope {
                buttonState = if (buttonState == ButtonState.Pressed) {
                    waitForUpOrCancellation()
                    ButtonState.Idle
                } else {
                    awaitFirstDown(false)
                    ButtonState.Pressed
                }
            }
        }
}