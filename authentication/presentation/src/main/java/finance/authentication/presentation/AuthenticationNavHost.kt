package finance.authentication.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import finance.authentication.presentation.forgotpassword.ForgotPasswordPage
import finance.authentication.presentation.login.LoginPage
import finance.authentication.presentation.newpassword.NewPasswordPage
import finance.authentication.presentation.pin.PinPage
import finance.authentication.presentation.signup.SignUpPage

private const val LOGIN = "login"
private const val FORGOT_PASSWORD = "forgot-password"
private const val SIGN_UP = "sign-up"
private const val PIN = "pin"
private const val NEW_PASSWORD = "new-password"

@Composable
fun AuthenticationNavHost(onGoToHome: () -> Unit) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = LOGIN) {
        composable(LOGIN) {
            LoginPage(
                onGotoForgotPassword = {
                    navController.navigate(FORGOT_PASSWORD)
                },
                onGotoSignUp = {
                    navController.navigate(SIGN_UP)
                },
                onGotoHome = onGoToHome,
            )
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