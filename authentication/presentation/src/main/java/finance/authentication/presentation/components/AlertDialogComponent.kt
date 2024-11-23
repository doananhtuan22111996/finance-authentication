package finance.authentication.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import finance.authentication.presentation.EMPTY_STRING
import vn.finance.authentication.presentation.R

@Composable
fun AlertExceptionDialogComponent(
    message: String,
    onDismissRequest: (() -> Unit) = {},
) {
    AlertDialog(
        icon = {
            Icon(
                modifier = Modifier.size(96.dp),
                imageVector = Icons.Filled.ErrorOutline,
                contentDescription = EMPTY_STRING
            )
        },
        title = {
            Text(
                text = stringResource(R.string.app_exception),
                style = MaterialTheme.typography.headlineSmall
            )
        },
        text = {
            Text(text = message, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = onDismissRequest) {
                Text(stringResource(R.string.accept))
            }
        },
    )
}