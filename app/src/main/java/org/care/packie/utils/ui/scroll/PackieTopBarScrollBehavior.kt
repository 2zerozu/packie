package org.care.packie.utils.ui.scroll

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.unit.Dp

@Stable
sealed class PackieTopBarScrollBehavior(
    val topBarScrollState: PackieTopBarScrollState
) : NestedScrollConnection {

    class ExitUntilCollapsed(
        topBarScrollState: PackieTopBarScrollState
    ) : PackieTopBarScrollBehavior(topBarScrollState) {
        override fun onPreScroll(
            available: Offset,
            source: NestedScrollSource
        ): Offset = when {
            available.y >= 0 -> Offset.Zero
            topBarScrollState.offset == -topBarScrollState.differenceHeight -> Offset.Zero
            else -> topBarScrollState.calculateOffset(available.y)
        }
        override fun onPostScroll(
            consumed: Offset,
            available: Offset,
            source: NestedScrollSource
        ): Offset = when {
            available.y <= 0 -> Offset.Zero
            topBarScrollState.offset == 0f -> Offset.Zero
            else -> topBarScrollState.calculateOffset(available.y)
        }

    }

    companion object {
        @Composable
        fun rememberExitUntilCollapsedScrollState(
            topBarScrollState: PackieTopBarScrollState
        ): ExitUntilCollapsed {
            return remember {
                ExitUntilCollapsed(topBarScrollState)
            }
        }

    }
}
