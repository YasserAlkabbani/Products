package com.plana.products.core.designsystem.card

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.plana.products.core.designsystem.PBackground
import com.plana.products.core.designsystem.PPreviews
import com.plana.products.core.designsystem.text.PText

@Composable
fun PCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
    onClick: () -> Unit
) {

    Card(
        modifier = modifier,
        content = content,
        onClick = onClick,
    )

}


@PPreviews
@Composable
fun PCardPreview() {
    PBackground {
        PCard(
            content = {
                PText(
                    modifier = Modifier,
                    text = "Hello"
                )
            },
            onClick = {}
        )
    }

}