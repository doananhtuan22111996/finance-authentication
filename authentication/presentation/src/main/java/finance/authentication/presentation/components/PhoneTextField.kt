package finance.authentication.presentation.components

import androidx.compose.foundation.clickable
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
import finance.authentication.presentation.isValidEmail
import java.util.Locale.IsoCountryCode

@Composable
fun PhoneTextField(
    countryCode: String = "+84",
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    onCountryCodeChange: () -> Unit,
    onValidator: ((value: String) -> String?)? = null
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
                text = stringResource(R.string.mobile_number),
                style = MaterialTheme.typography.labelMedium
            )
        },
        onValueChange = { text ->
            value = text
        },
        isError = isError,
        placeholder = {
            Text(text = stringResource(R.string._000_000_0000))
        },
        keyboardOptions = KeyboardOptions(
            autoCorrectEnabled = true,
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Next,
        ),
        supportingText = {
            if (isError) {
                Text(onValidator?.invoke(value) ?: EMPTY_STRING)
            }
        },
        prefix = {
            Text(text = countryCode,
                modifier = Modifier.clickable { onCountryCodeChange() })
        },
        singleLine = true,
        maxLines = 1,
    )
}