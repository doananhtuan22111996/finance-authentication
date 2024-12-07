package finance.authentication.presentation.login

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import finance.authentication.presentation.isValidEmail
import finance.authentication.presentation.isValidPassword
import vn.core.composex.uikit.Container
import vn.core.composex.uikit.alert.AlertExceptionDialogComponent
import vn.core.composex.uikit.dialog.FullScreenSuccessDialogComponent
import vn.core.composex.uikit.loading.FullScreenLoadingDialogComponent
import vn.core.composex.uikit.textField.AppEmailTextField
import vn.core.composex.uikit.textField.PasswordTextField
import vn.finance.authentication.presentation.R

@Composable
fun LoginPage(
    onGotoForgotPassword: () -> Unit,
    onGotoSignUp: () -> Unit,
    onGotoHome: () -> Unit,
) {
    val viewModel: LoginViewModel = hiltViewModel()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val isSuccessfully by viewModel.isSuccessfully.collectAsStateWithLifecycle()
    val appException by viewModel.appException.collectAsStateWithLifecycle()
    val isValidEmail by viewModel.isValidEmail.collectAsStateWithLifecycle()
    val isValidPassword by viewModel.isValidPassword.collectAsStateWithLifecycle()

    val invalidEmailMessage =
        stringResource(R.string.invalid_email_format_please_enter_a_valid_email)
    val invalidPasswordMessage =
        stringResource(R.string.invalid_password_format_please_enter_a_valid_password)

    val mContext = LocalContext.current

    Container(appBarTitle = stringResource(R.string.login)) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            AppEmailTextField(onValueChange = { text ->
                viewModel.onEmailChange(text)
            }, onValidator = { value ->
                val isValid = value.isValidEmail()
                viewModel.onValidEmail(isValid)
                if (isValid) {
                    null
                } else {
                    invalidEmailMessage
                }
            }, modifier = Modifier.padding(vertical = 8.dp))
            PasswordTextField(onValueChange = { text ->
                viewModel.onPasswordChange(text)
            }, onValidator = { value ->
                val isValid = value.isValidPassword()
                viewModel.onValidPassword(isValid)
                if (isValid) null
                else invalidPasswordMessage
            }, modifier = Modifier.padding(vertical = 8.dp))
            Button(
                onClick = {
                    viewModel.onLogin()
                },
                enabled = isValidEmail && isValidPassword,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(stringResource(R.string.login))
            }
            TextButton(
                onClick = onGotoForgotPassword, modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.forgot_password))
            }
            ElevatedButton(
                onClick = onGotoSignUp, modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.sign_up))
            }
            TextButton(
                onClick = {
                    Toast.makeText(mContext, "Coming soon", Toast.LENGTH_SHORT).show()
                }, modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.use_fingerprint_to_access))
            }

            if (isLoading) {
                FullScreenLoadingDialogComponent()
            }

            if (isSuccessfully) {
                FullScreenSuccessDialogComponent(content = {
                    Text(
                        stringResource(R.string.login_successful_welcome),
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center
                    )
                }, onDismissRequest = {
                    viewModel.onSuccessfullyDismiss()
                    onGotoHome()
                })
            }

            if (appException != null) {
                AlertExceptionDialogComponent(message = stringResource(R.string.login_failed_please_check_your_credentials),
                    onDismissRequest = { viewModel.onAppExceptionDismiss() })
            }
        }
    }
}
