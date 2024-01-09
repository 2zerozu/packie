package org.care.packie

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class StuffsPreviewProvider: PreviewParameterProvider<Map<String, Boolean>> {
    override val values: Sequence<Map<String, Boolean>>
        get() = sequenceOf(
            mapOf(
                "휴대폰" to false,
                "롤" to false,
                "하나둘셋넷" to false,
                "일이삼사오육칠팔구" to false,
                "어휴" to false,
                "에휴" to false,
                "오호1" to false,
                "오호2" to false,
                "오호3" to false,
                "오호4" to false,
                "오호5" to false,
                "오호6" to false,
                "오호7" to false,
                "오호8" to false,
                "안녕" to false
            )
        )


}