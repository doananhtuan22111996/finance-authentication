package finance.authentication.presentation.forgotpassword

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import finance.authentication.presentation.components.AlertExceptionDialogComponent
import finance.authentication.presentation.components.EmailTextField
import finance.authentication.presentation.components.FullScreenLoadingDialogComponent
import finance.authentication.presentation.components.IconButtonBack
import finance.authentication.presentation.isValidEmail
import vn.core.composex.uikit.Container
import vn.core.domain.ResultModel
import vn.finance.authentication.presentation.R

@Composable
fun ForgotPasswordPage(
    onGotoPin: () -> Unit,
    onGoBack: () -> Unit,
) {
    val viewModel: ForgotPasswordViewModel = hiltViewModel()
    val isValidEmail by viewModel.isValidEmail.collectAsStateWithLifecycle()
    val appException by viewModel.appException.collectAsStateWithLifecycle()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val invalidEmailMessage =
        stringResource(R.string.invalid_email_format_please_enter_a_valid_email)

    Container(appBarTitle = stringResource(R.string.forgot_password), navigationIcon = {
        IconButtonBack(onClick = onGoBack)
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                stringResource(R.string.reset_password),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
            )
            Text(stringResource(R.string.lorem_ipsum_dolor_sit_amet_consectetur_adipiscing_elit_sed_do_eiusmod_tempor_incididunt_ut_labore_et_dolore_magna_aliqua))
            EmailTextField(modifier = Modifier.padding(vertical = 16.dp), onValueChange = { text ->
                viewModel.onEmailChange(text)
            }, onValidator = { value ->
                val isValid = value.isValidEmail()
                viewModel.onValidEmail(isValid)
                if (isValid) {
                    null
                } else {
                    invalidEmailMessage
                }
            })
            Button(
                onClick = { viewModel.onFpEmail() },
                modifier = Modifier.fillMaxWidth(),
                enabled = isValidEmail
            ) {
                Text(stringResource(R.string.next_step))
            }
        }

        when (state) {
            is ResultModel.Loading -> FullScreenLoadingDialogComponent()
            is ResultModel.Success -> onGotoPin()
            else -> {}
        }

        if (appException != null) {
            AlertExceptionDialogComponent(message = stringResource(R.string.email_not_found),
                onDismissRequest = { viewModel.onAppExceptionDismiss() })
        }
    }
}
