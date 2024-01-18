package org.care.packie.utils.ui.scroll

import androidx.annotation.FloatRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun rememberPackieTopBarState(
    maxHeight: Dp,
    minHeight: Dp
): PackieTopBarScrollState {
    val maxHeightPx = with(LocalDensity.current) {
        maxHeight.toPx()
    }
    val minHeightPx = with(LocalDensity.current) {
        minHeight.toPx()
    }
    return remember {
        PackieTopBarScrollState(maxHeightPx, minHeightPx)
    }
}

@Stable
class PackieTopBarScrollState(
    val maxHeightPx: Float,
    val minHeightPx: Float
) {
    /**
     * 현재 스크롤 offset 값.
     * 양수 값인 경우 위로 스크롤(위에서 아래로 드레그) 된 offset을 나타낸다.
     * 음수 값인 경우 아래로 스크롤(아래에서 위로 드레그) 된 offset 을 나타낸다.
     */
    var offset by mutableFloatStateOf(0f)
        private set

    /**
     * 실질적 높이 값
     */
    var heightPx by mutableFloatStateOf(maxHeightPx)
        private set
    val height: Dp
        @Composable get() = (heightPx / LocalDensity.current.density).dp
    /**
     * 최대 최소 높이값 차이
     */
    val differenceHeight = maxHeightPx - minHeightPx
    /**
     * collapsing 이 진행된 progress
     * [maxHeightPx] 일때 0.0, [minHeightPx] 일때 1.0
     */
    val progress
        @FloatRange(from = 0.0, to = 1.0)
        get() = (maxHeightPx - heightPx)/differenceHeight
    val isCollapsed: Boolean
        get() = offset == -differenceHeight

    init {
        validateMinHeight(minHeightPx)
        validateMaxHeight(maxHeightPx, minHeightPx)
    }

    private fun validateMaxHeight(maxHeight: Float, minHeight: Float) {
        require(maxHeight >= minHeight) {
            "maxHeight must be greater than minHeight"
        }
    }

    private fun validateMinHeight(minHeight: Float) {
        require(minHeight >= 0f) {
            "minHeight must be greater than 0"
        }
    }

    fun calculateOffset(delta: Float): Offset {
        val oldOffset = offset
        val newOffset = (oldOffset + delta).coerceIn(-(differenceHeight), 0f)
        offset = newOffset
        heightPx = maxHeightPx + offset
        return Offset(0f, newOffset - oldOffset)
    }
}