package finance.authentication.presentation.pin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import finance.authentication.domain.usecase.FpPinUseCase
import finance.authentication.presentation.EMPTY_STRING
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vn.core.domain.ResultModel
import javax.inject.Inject

@HiltViewModel
class PinViewModel @Inject constructor(private val pinUseCase: FpPinUseCase) : ViewModel() {

    private val _pin = MutableStateFlow(EMPTY_STRING)
    val pin = _pin.asStateFlow()
    private val _state = MutableStateFlow<ResultModel<Nothing>>(ResultModel.Done)
    val state = _state.asStateFlow()
    private val _appException = MutableStateFlow<ResultModel.AppException?>(null)
    val appException = _appException.asStateFlow()
    private val _isValueInvalid = MutableStateFlow(false)
    val isValueInvalid = _isValueInvalid.asStateFlow()

    fun onPinChange(pin: String) {
        viewModelScope.launch {
            _pin.value = pin
        }
    }

    fun onPin() {
        viewModelScope.launch {
            pinUseCase.execute(params = arrayOf(_pin.value)).collect {
                _state.value = it
                if (it is ResultModel.AppException) {
                    _appException.value = it
                    _isValueInvalid.value = true
                }
            }
        }
    }

    fun onAppExceptionDismiss() {
        viewModelScope.launch {
            _appException.value = null
        }
    }

     fun onReset() {
        _isValueInvalid.value = false
        _pin.value = EMPTY_STRING
    }
}