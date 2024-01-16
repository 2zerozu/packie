package org.care.packie

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

object StuffsPreviewProvider {
    val mockStuffs: Map<String, Boolean> = listOf(
        "에어팟",
        "지갑",
        "애플 워치",
        "갤럭시 워치",
        "갤럭시 탭",
        "아이패드",
        "갤럭시북",
        "맥북",
        "립밤",
        "랜즈통",
        "칫솔",
        "치약"
    ).associateWith { false }

    val previewMockData = (1..100).associate { "item${it}" to false }

}