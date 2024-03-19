package org.care.packie.ui.component.ads

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import org.care.packie.R
import org.care.packie.ui.theme.PackieTheme

@Composable
private fun rememberAdRequest() = remember {
    AdRequest.Builder()
        .build()
}
@Composable
fun BannersAds(
    modifier: Modifier = Modifier,
    adRequest: AdRequest = rememberAdRequest()
) {
    val adId = stringResource(id = R.string.AD_MOB_BANNER_UNIT_ID)
    if (LocalInspectionMode.current) {
        Text(
            modifier = modifier
                .height(50.dp)
                .background(Color.Red),
            textAlign = TextAlign.Center,
            color = Color.White,
            text = "Advert Here",
        )
    } else {
        AndroidView(
            modifier = modifier,
            factory = { context ->
                AdView(context).apply {
                    setAdSize(AdSize.BANNER)
                    adUnitId = adId
                    loadAd(adRequest)
                }
            },
            update = {
                it.loadAd(adRequest)
            }
        )
    }
}

@Preview
@Composable
fun BannersAdsPreview() {
    PackieTheme {
        BannersAds(
            modifier = Modifier.fillMaxWidth()
        )
    }
}