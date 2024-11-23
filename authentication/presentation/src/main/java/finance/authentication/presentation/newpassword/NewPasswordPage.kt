package finance.authentication.presentation.newpassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import finance.authentication.presentation.components.AlertExceptionDialogComponent
import finance.authentication.presentation.components.FullScreenLoadingDialogComponent
import finance.authentication.presentation.components.FullScreenSuccessDialogComponent
import finance.authentication.presentation.components.IconButtonBack
import finance.authentication.presentation.components.PasswordTextField
import finance.authentication.presentation.isValidPassword
import vn.core.composex.uikit.Container
import vn.finance.authentication.presentation.R

@Composable
fun NewPasswordPage(
    onGotoLogin: () -> Unit,
    onGoBack: () -> Unit,
) {
    val invalidPasswordMessage =
        stringResource(R.string.invalid_password_format_please_enter_a_valid_password)

    val viewModel: NewPasswordViewModel = hiltViewModel()
    val isValidNewPassword = viewModel.isValidNewPassword.collectAsStateWithLifecycle()
    val isValidConfirmNewPassword =
        viewModel.isValidConfirmNewPassword.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val isSuccessfully by viewModel.isSuccessfully.collectAsStateWithLifecycle()
    val appException by viewModel.appException.collectAsStateWithLifecycle()

    Container(appBarTitle = stringResource(R.string.new_password), navigationIcon = {
        IconButtonBack(onClick = onGoBack)
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            PasswordTextField(modifier = Modifier.padding(vertical = 8.dp), label = {
                Text(
                    text = stringResource(R.string.new_password),
                    style = MaterialTheme.typography.labelMedium
                )
            }, onValueChange = { text ->
                viewModel.onNewPasswordChange(text)
            }, onValidator = { value ->
                val isValid = value.isValidPassword()
                viewModel.onValidPassword(isValid)
                if (isValid)
                    null
                else
                    invalidPasswordMessage
            })
            PasswordTextField(
                modifier = Modifier.padding(vertical = 8.dp),
                label = {
                    Text(
                        text = stringResource(R.string.confirm_new_password),
                        style = MaterialTheme.typography.labelMedium
                    )
                },
                onValueChange = { text ->
                    viewModel.onConfirmNewPasswordChange(text)
                },
                onValidator = { value ->
                    val isValid = value.isValidPassword()
                    viewModel.onValidConfirmPassword(isValid)
                    if (isValid)
                        null
                    else
                        invalidPasswordMessage
                }
            )
            Button(
                onClick = {
                    viewModel.onNewPassword()
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = isValidNewPassword.value && isValidConfirmNewPassword.value
            ) {
                Text(stringResource(R.string.change_password))
            }

            if (isLoading) {
                FullScreenLoadingDialogComponent()
            }
            if (isSuccessfully) {
                FullScreenSuccessDialogComponent(content = {
                    Text(
                        stringResource(R.string.password_has_been_changed_successfully),
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center
                    )
                }, onDismissRequest = {
                    viewModel.onSuccessfullyDismiss()
                    onGotoLogin()
                })
            }
            if (appException != null) {
                AlertExceptionDialogComponent(stringResource(R.string.password_change_failed_please_try_again),
                    onDismissRequest = {
                        viewModel.onAppExceptionDismiss()
                    })
            }
        }
    }
}
