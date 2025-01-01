package vn.finance.authentication

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vn.finance.authentication.api.GetLoggedInProvider
import javax.inject.Inject

@HiltViewModel
class DemoViewModel @Inject constructor(private val getLoggedInProvider: GetLoggedInProvider) : ViewModel() {

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = _isLoggedIn.asStateFlow()

    init {
        getLoggedIn()
    }

    private fun getLoggedIn() {
        viewModelScope.launch {
            getLoggedInProvider.getLoggedIn().collect {
                _isLoggedIn.emit(it)
            }
        }
    }
}