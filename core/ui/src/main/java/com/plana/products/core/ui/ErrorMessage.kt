package com.plana.products.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.plana.products.core.designsystem.PBackground
import com.plana.products.core.designsystem.PPreviews
import com.plana.products.core.designsystem.button.PButton
import com.plana.products.core.designsystem.text.PText

@Composable
fun PErrorMessage(
    errorTitle: String,
    errorSubtitle: String,
    buttonText: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(vertical = 12.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        PText(
            text = errorTitle,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        PText(
            text = errorSubtitle,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        PButton(
            text = buttonText,
            imageVector = Icons.Default.Replay,
            onClick = onRetry
        )
    }
}

@PPreviews
@Composable
fun PErrorMessagePreview() {
    PBackground {
        PErrorMessage(
            "ops There Something Error Happened",
            "PLease Try Aging Later..",
            "Try Aging",
            { }
        )
    }
}