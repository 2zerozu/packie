package org.care.packie.utils.ui.scroll

import android.util.Log
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource

@Stable
sealed class PackieTopBarScrollBehavior(
    val topBarState: PackieTopBarScrollState,
    val scrollableState: ScrollableState
) : NestedScrollConnection {

    class ExitUntilCollapsed(
        topBarScrollState: PackieTopBarScrollState,
        scrollableState: ScrollableState
    ) : PackieTopBarScrollBehavior(topBarScrollState, scrollableState) {

        override fun onPreScroll(
            available: Offset,
            source: NestedScrollSource
        ): Offset = when {
            scrollableState.canScrollForward && available.y < 0 -> {
                // 아래로 스크롤이 가능한 상태에서 아래로 스크롤 했을 경우, 부모에서 스크롤 전에 가져갈 오프셋 반환
                topBarState.calculateOffset(available.y)
            }
            topBarState.isCollapsed -> {
                // Collapsed 상태에서 스크롤 전에 부모에서 가져갈 오프셋 반환
                Offset.Zero
            }
            else -> {
                // 위로 스크롤이 가능한 상태에서 위로 스크롤 했을 경우 부모에서 스크롤 전에 가져갈 오프셋 반환
                Offset.Zero
            }
        }
        override fun onPostScroll(
            consumed: Offset,
            available: Offset,
            source: NestedScrollSource
        ): Offset = when {
            available.y <= 0 -> {
                //화면 아래로 내릴때 스크롤 후 자식 스크롤이 사용하고 남은 스크롤을 부모가 가져온다
                Offset.Zero
            }
            topBarState.offset == 0f -> {
                // Collapsed 상태에서 스크롤 후 부모에서 가져갈 오프셋 반환
                Offset.Zero
            }
            else -> {
                // 화면 위로 올릴때 스크롤 후 자식 스크롤이 사용하고 남은 스크롤을 부모가 가져온다.
                topBarState.calculateOffset(available.y)
            }
        }

    }

    companion object {
        @Composable
        fun rememberExitUntilCollapsedScrollState(
            topBarScrollState: PackieTopBarScrollState,
            scrollableState: ScrollableState
        ): ExitUntilCollapsed {
            return remember {
                ExitUntilCollapsed(topBarScrollState, scrollableState)
            }
        }

    }
}
