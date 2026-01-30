package com.chauxdevapps.catwalletapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chauxdevapps.catwalletapp.R

@Composable
fun ErrorScreen(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Cat illustration
        Text(
            text = "🐱",
            style = MaterialTheme.typography.displayLarge,
            fontSize = 80.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Error title
        Text(
            text = stringResource(R.string.error_no_cats_title),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Friendly error description
        Text(
            text = stringResource(R.string.error_no_cats_description),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Retry button
        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            shape = MaterialTheme.shapes.large
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.retry_button),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}
