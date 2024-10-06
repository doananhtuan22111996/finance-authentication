package finance.authentication.presentation.pin

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import finance.authentication.presentation.OTP_LENGTH
import finance.authentication.presentation.components.AlertExceptionDialogComponent
import finance.authentication.presentation.components.FullScreenLoadingDialogComponent
import finance.authentication.presentation.components.IconButtonBack
import finance.authentication.presentation.components.OtpComponent
import vn.core.composex.uikit.Container
import vn.core.domain.ResultModel
import vn.finance.authentication.R

@Composable
fun PinPage(
    onGoBack: () -> Unit,
    onGotoNewPassword: () -> Unit,
) {
    val viewModel: PinViewModel = hiltViewModel()
    val isValueInvalid by viewModel.isValueInvalid.collectAsStateWithLifecycle()
    val pin by viewModel.pin.collectAsStateWithLifecycle()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val appException by viewModel.appException.collectAsStateWithLifecycle()

    Container(appBarTitle = stringResource(R.string.security_pin), navigationIcon = {
        IconButtonBack(onClick = onGoBack)
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .padding(vertical = 32.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                OtpComponent(
                    otp = pin,
                    isValueInvalid = isValueInvalid,
                    onValueChange = { pin, _ ->
                        viewModel.onPinChange(pin)
                    },
                )
            }
            Button(
                onClick = { viewModel.onPin() },
                enabled = pin.trim().length == OTP_LENGTH,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.accept))
            }
        }

        when (state) {
            is ResultModel.Loading -> FullScreenLoadingDialogComponent()
            is ResultModel.Success -> onGotoNewPassword()
            else -> {}
        }

        if (appException != null) {
            AlertExceptionDialogComponent(message = stringResource(R.string.incorrect_pin_please_try_again),
                onDismissRequest = {
                    viewModel.onAppExceptionDismiss()
                    viewModel.onReset()
                })
        }
    }
}
