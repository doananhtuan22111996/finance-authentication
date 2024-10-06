package finance.authentication.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import finance.authentication.domain.usecase.LoginUseCase
import finance.authentication.presentation.EMPTY_STRING
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vn.core.domain.ResultModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    private val _isSuccessfully = MutableStateFlow(false)
    val isSuccessfully = _isSuccessfully.asStateFlow()
    private val _appException = MutableStateFlow<ResultModel.AppException?>(null)
    val appException = _appException.asStateFlow()

    private val _email = MutableStateFlow(EMPTY_STRING)
    private val _password = MutableStateFlow(EMPTY_STRING)
    private val _isValidEmail = MutableStateFlow(false)
    val isValidEmail = _isValidEmail.asStateFlow()
    private val _isValidPassword = MutableStateFlow(false)
    val isValidPassword = _isValidPassword.asStateFlow()

    fun onEmailChange(email: String) {
        viewModelScope.launch {
            _email.value = email
        }
    }

    fun onPasswordChange(password: String) {
        viewModelScope.launch {
            _password.value = password
        }
    }

    fun onValidEmail(isValid: Boolean) {
        _isValidEmail.value = isValid
    }

    fun onValidPassword(isValid: Boolean) {
        _isValidPassword.value = isValid
    }

    fun onLogin() {
        viewModelScope.launch {
            loginUseCase.execute(params = arrayOf(_email.value, _password.value)).collect {
                when (it) {
                    is ResultModel.Success -> _isSuccessfully.value = true
                    is ResultModel.AppException -> _appException.value = it
                    is ResultModel.Loading -> _isLoading.value = true
                    else -> _isLoading.value = false
                }
            }
        }
    }

    fun onSuccessfullyDismiss() {
        _isSuccessfully.value = false
    }

    fun onAppExceptionDismiss() {
        _appException.value = null
    }
}