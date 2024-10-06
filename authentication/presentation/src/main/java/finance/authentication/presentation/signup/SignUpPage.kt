package finance.authentication.presentation.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import finance.authentication.presentation.components.AppDatePicker
import finance.authentication.presentation.components.AppHtmlText
import finance.authentication.presentation.components.AppTextField
import finance.authentication.presentation.components.EmailTextField
import finance.authentication.presentation.components.FullScreenLoadingDialogComponent
import finance.authentication.presentation.components.FullScreenSuccessDialogComponent
import finance.authentication.presentation.components.IconButtonBack
import finance.authentication.presentation.components.PasswordTextField
import finance.authentication.presentation.components.PhoneTextField
import finance.authentication.presentation.isValidEmail
import finance.authentication.presentation.isValidPassword
import finance.authentication.presentation.isValidPhoneNumber
import vn.core.composex.uikit.Container
import vn.finance.authentication.R

@Composable
fun SignUpPage(onGoBack: () -> Unit, onGoToLogin: () -> Unit) {
    val requiredFieldMessage = stringResource(R.string.this_field_is_required)
    val invalidPhoneMessage = stringResource(R.string.please_enter_a_valid_phone_number)
    val invalidEmailMessage =
        stringResource(R.string.invalid_email_format_please_enter_a_valid_email)
    val invalidPasswordMessage =
        stringResource(R.string.invalid_password_format_please_enter_a_valid_password)

    val viewModel: SignUpViewModel = hiltViewModel()
    val isValidEmail by viewModel.isValidEmail.collectAsStateWithLifecycle()
    val isValidUsername by viewModel.isValidUsername.collectAsStateWithLifecycle()
    val isValidPassword by viewModel.isValidPassword.collectAsStateWithLifecycle()
    val isValidConfirmPassword by viewModel.isValidConfirmPassword.collectAsStateWithLifecycle()
    val isValidPhone by viewModel.isValidPhone.collectAsStateWithLifecycle()
    val isValidDob by viewModel.isValidDob.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val isSuccessfully by viewModel.isSuccessfully.collectAsStateWithLifecycle()
    val appException by viewModel.appException.collectAsStateWithLifecycle()

    Container(appBarTitle = stringResource(R.string.sign_up), navigationIcon = {
        IconButtonBack(onClick = onGoBack)
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            AppTextField(label = stringResource(R.string.full_name),
                placeHolder = stringResource(R.string.input_full_name),
                onValueChange = { value ->
                    viewModel.onFullNameChange(value)
                },
                onValidator = { value ->
                    val isValid = value.isNotEmpty()
                    viewModel.onValidUsername(isValid)
                    if (isValid) {
                        null
                    } else {
                        requiredFieldMessage
                    }
                }

            )
            EmailTextField(onValueChange = { value ->
                viewModel.onEmailChange(value)
            }, onValidator = { value ->
                val isValid = value.isValidEmail()
                viewModel.onValidEmail(isValid)
                if (isValid) {
                    null
                } else {
                    invalidEmailMessage
                }
            }, modifier = Modifier.padding(vertical = 8.dp))
            PhoneTextField(countryCode = stringResource(R.string._84), onValueChange = { value ->
                viewModel.onPhoneChange(value)
            }, onCountryCodeChange = {}, onValidator = { value ->
                val isValid = value.isNotEmpty() && value.isValidPhoneNumber()
                viewModel.onValidPhone(isValid)
                if (value.isEmpty()) {
                    requiredFieldMessage
                } else if (!value.isValidPhoneNumber()) {
                    invalidPhoneMessage
                } else {
                    null
                }
            })
            AppDatePicker(stringResource(R.string.date_of_birth),
                stringResource(R.string.dd_mm_yyyy),
                title = {
                    Text(
                        stringResource(R.string.date_of_birth), modifier = Modifier.padding(
                            PaddingValues(start = 24.dp, end = 12.dp, top = 16.dp)
                        )
                    )
                },
                onValueChange = { value ->
                    viewModel.onDobChange(value)
                },
                onValidator = { value ->
                    val isValid = value.isNotEmpty()
                    viewModel.onValidDob(isValid)
                    if (isValid) null else requiredFieldMessage
                })
            PasswordTextField(onValueChange = { value ->
                viewModel.onPasswordChange(value)
            }, onValidator = { value ->
                val isValid = value.isValidPassword()
                viewModel.onValidPassword(isValid)
                if (isValid) {
                    null
                } else {
                    invalidPasswordMessage
                }
            }, modifier = Modifier.padding(vertical = 8.dp))
            PasswordTextField(modifier = Modifier.padding(vertical = 8.dp), label = {
                Text(
                    text = stringResource(R.string.confirm_password),
                    style = MaterialTheme.typography.labelMedium
                )
            }, onValueChange = { value ->
                viewModel.onConfirmPasswordChange(value)
            }, onValidator = { value ->
                val isValid = value.isValidPassword()
                viewModel.onValidConfirmPassword(isValid)
                if (isValid) {
                    null
                } else {
                    invalidPasswordMessage
                }
            })
            Box(modifier = Modifier.padding(vertical = 8.dp)) {
                AppHtmlText(stringResource(R.string.t_n_c))
            }
            Button(
                onClick = {
                    viewModel.onSignUp()
                },
                enabled = isValidEmail && isValidUsername && isValidPassword && isValidConfirmPassword && isValidPhone && isValidDob,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.sign_up))
            }
        }

        if (isLoading) {
            FullScreenLoadingDialogComponent()
        }

        if (isSuccessfully) {
            FullScreenSuccessDialogComponent(onDismissRequest = {
                viewModel.onDismissSuccessfully()
                onGoToLogin()
            }) {
                Text(
                    stringResource(R.string.success_your_account_has_been_created),
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
            }
        }

        if (appException != null) {
            AlertExceptionDialogComponent(message = stringResource(R.string.unable_to_create_your_account_please_verify_your_information_and_try_again),
                onDismissRequest = { viewModel.onDismissException() })
        }
    }
}
