package finance.authentication.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import finance.authentication.presentation.forgotpassword.ForgotPasswordPage
import finance.authentication.presentation.login.LoginPage
import finance.authentication.presentation.newpassword.NewPasswordPage
import finance.authentication.presentation.pin.PinPage
import finance.authentication.presentation.signup.SignUpPage
import vn.finance.navigation.NavigationKey
import vn.finance.navigation.NavigationManager

const val LOGIN = "login"
const val FORGOT_PASSWORD = "forgot-password"
const val SIGN_UP = "sign-up"
const val PIN = "pin"
const val NEW_PASSWORD = "new-password"
const val BIOMETRIC = "biometric"

@Composable
fun AuthenticationNavHost(navigationManager: NavigationManager, navController: NavHostController) {
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = LOGIN) {
        composable(LOGIN) {
            LoginPage(onGotoForgotPassword = {
                navController.navigate(FORGOT_PASSWORD)
            }, onGotoSignUp = {
                navController.navigate(SIGN_UP)
            }, onGotoHome = {
                navigationManager.startActivityByKey(context, NavigationKey.Home())
                context.getActivity()?.finish()
            })
        }
        composable(FORGOT_PASSWORD) {
            ForgotPasswordPage(onGotoPin = { navController.navigate(PIN) }, onGoBack = {
                navController.popBackStack()
            })
        }
        composable(PIN) {
            PinPage(onGoBack = {
                navController.popBackStack()
            }, onGotoNewPassword = {
                navController.navigate(NEW_PASSWORD)
            })
        }
        composable(NEW_PASSWORD) {
            NewPasswordPage(onGotoLogin = {
                navController.navigate(LOGIN) {
                    popUpTo(LOGIN) { inclusive = true }
                }
            }, onGoBack = {
                navController.popBackStack()
            })
        }
        composable(SIGN_UP) {
            SignUpPage(onGoBack = {
                navController.popBackStack()
            }, onGoToLogin = {
                navController.navigate(LOGIN) {
                    popUpTo(LOGIN) { inclusive = true }
                }
            })
        }
    }
}