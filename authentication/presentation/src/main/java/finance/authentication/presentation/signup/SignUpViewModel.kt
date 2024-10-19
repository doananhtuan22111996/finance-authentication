package finance.authentication.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import vn.finance.authentication.business.domain.usecase.SignupUseCase
import finance.authentication.presentation.EMPTY_STRING
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vn.core.domain.ResultModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signupUseCase: SignupUseCase) : ViewModel() {
    private val _username = MutableStateFlow(EMPTY_STRING)
    private val _email = MutableStateFlow(EMPTY_STRING)
    private val _phone = MutableStateFlow(EMPTY_STRING)
    private val _dob = MutableStateFlow(EMPTY_STRING)
    private val _password = MutableStateFlow(EMPTY_STRING)
    private val _confirmPassword = MutableStateFlow(EMPTY_STRING)
    private val _isValidEmail = MutableStateFlow(false)
    val isValidEmail = _isValidEmail.asStateFlow()
    private val _isValidUsername = MutableStateFlow(false)
    val isValidUsername = _isValidUsername.asStateFlow()
    private val _isValidPassword = MutableStateFlow(false)
    val isValidPassword = _isValidPassword.asStateFlow()
    private val _isValidConfirmPassword = MutableStateFlow(false)
    val isValidConfirmPassword = _isValidConfirmPassword.asStateFlow()
    private val _isValidPhone = MutableStateFlow(false)
    val isValidPhone = _isValidPhone.asStateFlow()
    private val _isValidDob = MutableStateFlow(false)
    val isValidDob = _isValidDob.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    private val _isSuccessfully = MutableStateFlow(false)
    val isSuccessfully = _isSuccessfully.asStateFlow()
    private val _appException = MutableStateFlow<ResultModel.AppException?>(null)
    val appException = _appException.asStateFlow()

    fun onFullNameChange(value: String) {
        _username.value = value
    }

    fun onEmailChange(value: String) {
        _email.value = value
    }

    fun onPhoneChange(value: String) {
        _phone.value = value
    }

    fun onDobChange(value: String) {
        _dob.value = value
    }

    fun onPasswordChange(value: String) {
        _password.value = value
    }

    fun onConfirmPasswordChange(value: String) {
        _confirmPassword.value = value
    }

    fun onValidEmail(isValid: Boolean) {
        _isValidEmail.value = isValid
    }

    fun onValidUsername(isValid: Boolean) {
        _isValidUsername.value = isValid
    }

    fun onValidPassword(isValid: Boolean) {
        _isValidPassword.value = isValid
    }

    fun onValidConfirmPassword(isValid: Boolean) {
        _isValidConfirmPassword.value = isValid && _confirmPassword.value == _password.value
    }

    fun onValidPhone(isValid: Boolean) {
        _isValidPhone.value = isValid
    }

    fun onValidDob(isValid: Boolean) {
        _isValidDob.value = isValid
    }

    fun onSignUp() {
        viewModelScope.launch {
            signupUseCase.execute(
                params = arrayOf(
                    _username.value,
                    _email.value,
                    _phone.value,
                    _dob.value,
                    _password.value,
                    _confirmPassword.value
                )
            ).collect {
                when (it) {
                    is ResultModel.Loading -> _isLoading.value = true
                    is ResultModel.Success -> _isSuccessfully.value = true
                    is ResultModel.AppException -> _appException.value = it
                    else -> _isLoading.value = false
                }
            }
        }
    }

    fun onDismissSuccessfully() {
        _isSuccessfully.value = false
    }

    fun onDismissException() {
        _appException.value = null
    }
}