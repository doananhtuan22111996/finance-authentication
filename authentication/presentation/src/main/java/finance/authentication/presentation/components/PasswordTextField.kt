package finance.authentication.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import finance.authentication.presentation.EMPTY_STRING
import finance.authentication.presentation.isValidPassword

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    onValidator: ((String) -> String?)? = null
) {
    var value by rememberSaveable { mutableStateOf(EMPTY_STRING) }
    var isError by rememberSaveable { mutableStateOf(false) }
    var isShowPassword by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(value) {
        onValueChange(value)
        val mError = onValidator?.invoke(value)
        isError = mError?.isNotEmpty() == true
    }

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        label = label ?: {
            Text(
                text = stringResource(R.string.password),
                style = MaterialTheme.typography.labelMedium
            )
        },
        onValueChange = { text ->
            value = text
        },
        trailingIcon = {
            IconButton(onClick = {
                isShowPassword = !isShowPassword
            }) {
                Icon(
                    if (isShowPassword) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                    if (isShowPassword) stringResource(R.string.visibilityoff) else stringResource(R.string.visibility),
                )
            }
        },
        isError = isError,
        placeholder = {
            Text(text = stringResource(R.string.example_password))
        },
        keyboardOptions = KeyboardOptions(
            autoCorrectEnabled = true,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        supportingText = {
            if (isError) {
                Text(onValidator?.invoke(value) ?: EMPTY_STRING)
            }
        },
        visualTransformation = if (isShowPassword) VisualTransformation.None else PasswordVisualTransformation(),
        singleLine = true,
        maxLines = 1,
    )
}