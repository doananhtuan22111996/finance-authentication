package vn.finance.authentication.api

import androidx.compose.runtime.Composable

interface AuthenticationApi {
    val path: String

    @Composable
    fun AuthenticationPage(onGotoHome: () -> Unit)
}