package org.care.packie.ui.component.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.care.packie.R
import org.care.packie.ui.theme.PackieDesignSystem

@Composable
fun SettingPopupMenu(
    onClickPrivacyPolicy: () -> Unit,
    onClickTerms: () -> Unit,
    onClickContactUs: () -> Unit,
    onClickDeveloperInfo: () -> Unit,
) {
    var isPopupOpen by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.clickable { isPopupOpen = true }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_setting), // 설정 아이콘에 해당하는 리소스를 넣어주세요
            contentDescription = null,
            tint = PackieDesignSystem.colors.white, // 아이콘 색상을 설정하세요
            modifier = Modifier.padding(16.dp)
        )

        DropdownMenu(
            expanded = isPopupOpen,
            onDismissRequest = { isPopupOpen = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    isPopupOpen = false
                    onClickPrivacyPolicy()
                },
                text = {
                    Text(
                        text = "개인정보 보호 정책",
                        style = PackieDesignSystem.typography.content
                    )
                }
            )

            DropdownMenuItem(
                onClick = {
                    isPopupOpen = false
                    onClickTerms()
                },
                text = {
                    Text(
                        text = "서비스 이용 약관",
                        style = PackieDesignSystem.typography.content
                    )
                }
            )

            DropdownMenuItem(
                onClick = {
                    isPopupOpen = false
                    onClickContactUs()
                },
                text = {
                    Text(
                        text = "문의하기",
                        style = PackieDesignSystem.typography.content
                    )
                }
            )

            DropdownMenuItem(
                onClick = {
                    isPopupOpen = false
                    onClickDeveloperInfo()
                },
                text = {
                    Text(
                        text = "개발자 정보",
                        style = PackieDesignSystem.typography.content
                    )
                }
            )
        }
    }
}
