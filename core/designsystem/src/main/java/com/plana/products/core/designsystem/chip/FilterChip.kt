package com.plana.products.core.designsystem.chip

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plana.products.core.designsystem.PBackground
import com.plana.products.core.designsystem.PPreviews
import com.plana.products.core.designsystem.text.PText

@Composable
fun PFilterChip(
    modifier: Modifier = Modifier,
    label: String,
    selected: Boolean,
    onSelect: (String) -> Unit,
) {
    FilterChip(
        modifier = modifier.padding(horizontal = 2.dp),
        selected = selected,
        onClick = { onSelect(label) },
        label = { PText(text = label) },
    )
}


@PPreviews
@Composable
fun PFilterChipNotSelectedPreview() {
    PBackground {
        PFilterChip(
            label = "Chip",
            selected = false,
            onSelect = {}
        )
    }
}

@PPreviews
@Composable
fun PFilterChipSelectedPreview() {
    PBackground {
        PFilterChip(
            label = "Chip",
            selected = true,
            onSelect = {}
        )
    }
}