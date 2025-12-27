package com.plana.products.core.designsystem

import android.content.res.Configuration
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plana.products.core.designsystem.theme.ProductsTheme

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Light theme",
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark theme",
)
annotation class PPreviews


@Composable
fun PBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    ProductsTheme {
        Surface(
            modifier = modifier,
            content = {
                CompositionLocalProvider(LocalAbsoluteTonalElevation provides 0.dp) {
                    content()
                }
            }
        )
    }
}