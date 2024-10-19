package finance.authentication.presentation.forgotpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import vn.finance.authentication.business.domain.usecase.FpEmailUseCase
import finance.authentication.presentation.EMPTY_STRING
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vn.core.domain.ResultModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(private val fpEmailUseCase: FpEmailUseCase) :
    ViewModel() {

    private val _email = MutableStateFlow(EMPTY_STRING)

    private val _isValidEmail = MutableStateFlow(false)
    val isValidEmail = _isValidEmail.asStateFlow()
    private val _state = MutableStateFlow<ResultModel<Nothing>>(ResultModel.Done)
    val state = _state.asStateFlow()
    private val _appException = MutableStateFlow<ResultModel.AppException?>(null)
    val appException = _appException.asStateFlow()

    fun onEmailChange(email: String) {
        viewModelScope.launch {
            _email.value = email
        }
    }

    fun onValidEmail(isValid: Boolean) {
        viewModelScope.launch {
            _isValidEmail.value = isValid
        }
    }

    fun onFpEmail() {
        viewModelScope.launch {
            fpEmailUseCase.execute(_email.value).collect {
                _state.value = it
                if (it is ResultModel.AppException) {
                    _appException.value = it
                }
            }
        }
    }

    fun onAppExceptionDismiss() {
        viewModelScope.launch {
            _appException.value = null
        }
    }
}