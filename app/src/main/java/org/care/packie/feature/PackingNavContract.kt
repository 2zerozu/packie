package org.care.packie.feature

object PackingNavContract {
    const val ROUTE = "packing"

    object Category {
        const val ROUTE = "category"
    }

    object Stuffs {
        const val CATEGORY_ARGUMENT = "category"
        private const val BASE_ROUTE = "stuffs"

        fun getRoute(category: String? = null): String {
            return category?.let {
                "$BASE_ROUTE/${it}"
            } ?: "$BASE_ROUTE/{$CATEGORY_ARGUMENT}"
        }
    }
}