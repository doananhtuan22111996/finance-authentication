package finance.authentication.presentation.newpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import finance.authentication.domain.usecase.FpNewPasswordUseCase
import finance.authentication.presentation.EMPTY_STRING
import finance.authentication.presentation.isValidPassword
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vn.core.domain.ResultModel
import javax.inject.Inject

@HiltViewModel
class NewPasswordViewModel @Inject constructor(private val fpNewPasswordUseCase: FpNewPasswordUseCase) :
    ViewModel() {

    private val _newPassword = MutableStateFlow(EMPTY_STRING)
    private val _confirmNewPassword = MutableStateFlow(EMPTY_STRING)
    private val _isValidNewPassword = MutableStateFlow(false)
    val isValidNewPassword = _isValidNewPassword.asStateFlow()
    private val _isValidConfirmNewPassword = MutableStateFlow(false)
    val isValidConfirmNewPassword = _isValidConfirmNewPassword.asStateFlow()
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    private val _isSuccessfully = MutableStateFlow(false)
    val isSuccessfully = _isSuccessfully.asStateFlow()
    private val _appException = MutableStateFlow<ResultModel.AppException?>(null)
    val appException = _appException.asStateFlow()

    fun onNewPasswordChange(value: String) {
        viewModelScope.launch {
            _newPassword.value = value
        }
    }

    fun onValidPassword(isValid: Boolean) {
        _isValidNewPassword.value = isValid && _confirmNewPassword.value == _newPassword.value
    }

    fun onConfirmNewPasswordChange(value: String) {
        viewModelScope.launch {
            _confirmNewPassword.value = value
        }
    }

    fun onValidConfirmPassword(isValid: Boolean) {
        _isValidConfirmNewPassword.value = isValid
    }

    fun onNewPassword() {
        viewModelScope.launch {
            fpNewPasswordUseCase.execute(
                params = arrayOf(
                    _newPassword.value, _confirmNewPassword.value
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

    fun onSuccessfullyDismiss() {
        _isSuccessfully.value = false
    }

    fun onAppExceptionDismiss() {
        _appException.value = null
    }
}