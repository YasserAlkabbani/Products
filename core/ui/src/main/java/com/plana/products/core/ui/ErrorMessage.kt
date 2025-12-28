package com.plana.products.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.plana.products.core.designsystem.PBackground
import com.plana.products.core.designsystem.PPreviews
import com.plana.products.core.designsystem.button.PButton
import com.plana.products.core.designsystem.button.PTextButton
import com.plana.products.core.designsystem.card.PCardContainer
import com.plana.products.core.designsystem.icon.PIcon
import com.plana.products.core.designsystem.text.PText


@Composable
fun PErrorCustomMessage(
    modifier: Modifier = Modifier,
    errorMessage: String?,
    onRetry: () -> Unit
) {
    PErrorMessage(
        modifier = modifier,
        errorTitle = stringResource(R.string.ops_theres_something_wrong),
        errorSubtitle = errorMessage ?: stringResource(R.string.unknown_error),
        buttonText = stringResource(R.string.try_again),
        onRetry = onRetry
    )
}

@Composable
fun PErrorConnectionIssue(
    modifier: Modifier = Modifier,
    onRetry: () -> Unit
) {
    PErrorMessage(
        modifier = modifier,
        errorTitle = stringResource(R.string.connection_issue),
        errorSubtitle = stringResource(R.string.please_check_your_internet_connection),
        buttonText = stringResource(R.string.try_again),
        onRetry = onRetry
    )
}

@Composable
fun PErrorConnectionTimeout(
    modifier: Modifier = Modifier,
    onRetry: () -> Unit
) {
    PErrorMessage(
        modifier = modifier,
        errorTitle = stringResource(R.string.slow_internet_connection),
        errorSubtitle = stringResource(R.string.make_sure_to_have_a_good_internet_connection),
        buttonText = stringResource(R.string.try_again),
        onRetry = onRetry
    )
}

@Composable
fun PErrorMessage(
    modifier: Modifier = Modifier,
    errorTitle: String,
    errorSubtitle: String,
    buttonText: String,
    onRetry: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.errorContainer
        ),
        onClick = onRetry
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            PText(
                text = errorTitle,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )
            PText(
                text = errorSubtitle,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                PText(text = buttonText)
                PIcon(imageVector = Icons.Default.Replay)
            }
        }
    }
}

@PPreviews
@Composable
fun PErrorCustomMessagePreview() {
    PBackground {
        PErrorCustomMessage(
            errorMessage = "ops There Something Error Happened",
            onRetry = { }
        )
    }
}

@PPreviews
@Composable
fun PErrorConnectionTimeoutPreview() {
    PBackground {
        PErrorConnectionTimeout(onRetry = { })
    }
}

@PPreviews
@Composable
fun PErrorConnectionIssuePreview() {
    PBackground {
        PErrorConnectionIssue(onRetry = { })
    }
}

@PPreviews
@Composable
fun PErrorMessagePreview() {
    PBackground {
        PErrorMessage(
            errorTitle = "ops There Something Error Happened",
            errorSubtitle = "PLease Try Aging Later..",
            buttonText = "Try Aging",
            onRetry = { }
        )
    }
}