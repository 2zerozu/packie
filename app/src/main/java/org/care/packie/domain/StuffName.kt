package org.care.packie.domain

data class StuffName(private val name: String) {
    fun limitMaxLength(limit: Int, overflowToken: String = OVERFLOW_ELLIPSIS): String {
        return if (name.length > limit) {
            "${name.substring(0 until limit)}${overflowToken}"
        } else {
            name
        }
    }

    companion object {
        private const val OVERFLOW_ELLIPSIS = "⋯"
        const val LIMITE_LENGHT = 7
    }
}
