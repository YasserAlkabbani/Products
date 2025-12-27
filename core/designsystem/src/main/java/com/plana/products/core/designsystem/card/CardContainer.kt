package com.plana.products.core.designsystem.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plana.products.core.designsystem.PBackground
import com.plana.products.core.designsystem.PPreviews
import com.plana.products.core.designsystem.text.PText

@Composable
fun CardContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = modifier,
        content = {
            Box(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
                content()
            }
        },
    )
}

@PPreviews
@Composable
fun CardContainerPreview() {
    PBackground {
        CardContainer {
            PText(text = "Product Item")
        }
    }
}

