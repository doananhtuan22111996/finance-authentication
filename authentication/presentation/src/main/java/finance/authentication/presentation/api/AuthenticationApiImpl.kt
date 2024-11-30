package finance.authentication.presentation.api

import androidx.compose.runtime.Composable
import finance.authentication.presentation.PATH
import vn.finance.authentication.api.AuthenticationApi
import finance.authentication.presentation.AuthenticationNavHost as Page

class AuthenticationApiImpl : AuthenticationApi {
    override val path: String
        get() = PATH

    @Composable
    override fun AuthenticationPage(onGotoHome: () -> Unit) {
        Page(onGoToHome = onGotoHome)
    }
}