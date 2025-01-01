package vn.finance.authentication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import dagger.hilt.android.AndroidEntryPoint
import finance.authentication.presentation.AuthenticationNavHost
import finance.authentication.presentation.getActivity
import vn.finance.onboarding.api.OnboardingApi
import vn.finance.theme.AppTheme
import javax.inject.Inject

@AndroidEntryPoint
class AuthenticationActivity : ComponentActivity() {

    @Inject
    lateinit var onboardingApi: OnboardingApi

    private val viewModel : DemoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            AppTheme {
                DemoNavHost(navController)
            }
        }
    }


    @Composable
    fun DemoNavHost(navController: NavHostController) {
        val isLoggedIn by viewModel.isLoggedIn.collectAsStateWithLifecycle()
        val context = LocalContext.current
        NavHost(navController = navController, startDestination = onboardingApi.path) {
            composable(onboardingApi.path) {
                onboardingApi.OnboardingPage(navigateTo = {
                    navController.navigate("authentication", navOptions {
                        popUpTo(onboardingApi.path) {
                            inclusive = true
                        }
                    })
                })
            }
            composable("authentication") {
                AuthenticationNavHost(onGoToHome = {
                    println("Func onGoToHome invoked")
                    context.getActivity()?.finish()
                })
            }
        }
    }
}

