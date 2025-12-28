package com.plana.products.core.designsystem.progress

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plana.products.core.designsystem.PBackground
import com.plana.products.core.designsystem.PPreviews

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun PLinearProgress() {
    LinearWavyProgressIndicator(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    )
}

@PPreviews
@Composable
fun PLinearProgressPreview() {
    PBackground {
        PLinearProgress()
    }
}