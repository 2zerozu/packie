package org.care.packie.feature

import android.net.Uri

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

    object Url {
        private const val PRIVACY_POLICY_URL = "https://2zerozu.notion.site/ccb6d3ff81f742ba9152d31de8a62ab1?pvs=4"
        private const val TERMS_URL = "https://2zerozu.notion.site/abbe8debe4f14d19b4a62b3f8db459bb?pvs=4"
        private const val CONTACT_US_URL = "https://walla.my/packie"
        private const val DEVELOPER_INFO_URL = "https://2zerozu.notion.site/a14c462a913f42029f8b05c596f5441b?pvs=4"

        val privacyPolicyUri = Uri.parse(PRIVACY_POLICY_URL)
        val termsUri = Uri.parse(TERMS_URL)
        val contactUsUri = Uri.parse(CONTACT_US_URL)
        val developerInfoUri = Uri.parse(DEVELOPER_INFO_URL)
    }
}