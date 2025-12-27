package com.plana.products.core.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plana.products.core.designsystem.PBackground
import com.plana.products.core.designsystem.PPreviews
import com.plana.products.core.designsystem.card.CardContainer
import com.plana.products.core.designsystem.icon.PIcon
import com.plana.products.core.designsystem.text.PText

@Composable
fun PRating(
    modifier: Modifier = Modifier,
    rating: Float,
    ratingCount: Int
) {

    CardContainer {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PIcon(
                modifier= Modifier,
                imageVector = Icons.Default.StarRate
            )
            Spacer(modifier = Modifier.width(4.dp))
            PText(
                text = rating.toString(),
                style = MaterialTheme.typography.titleMedium
            )
            PText(
                text = "(${ratingCount})",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }

}

@PPreviews
@Composable
fun PRatingPreview() {
    PBackground {
        PRating(rating = 4.6f, ratingCount = 150)
    }
}