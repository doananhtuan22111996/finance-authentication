package finance.authentication.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import vn.finance.authentication.R
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.Modifier
import finance.authentication.presentation.EMPTY_STRING

@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    onValidator: ((String) -> String?)? = null
) {
    var value by rememberSaveable { mutableStateOf(EMPTY_STRING) }
    var isError by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(value) {
        onValueChange(value)
        val mError = onValidator?.invoke(value)
        isError = !mError.isNullOrEmpty()
    }

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        label = {
            Text(
                text = stringResource(R.string.username_or_email),
                style = MaterialTheme.typography.labelMedium
            )
        },
        onValueChange = { text ->
            value = text
        },
        isError = isError,
        placeholder = {
            Text(text = stringResource(R.string.example_email))
        },
        keyboardOptions = KeyboardOptions(
            autoCorrectEnabled = true,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
        ),
        supportingText = {
            if (isError) {
                Text(onValidator?.invoke(value) ?: EMPTY_STRING)
            }
        },
        singleLine = true,
        maxLines = 1,
    )
}