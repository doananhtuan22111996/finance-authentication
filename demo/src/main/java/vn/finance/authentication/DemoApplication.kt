package vn.finance.authentication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import finance.authentication.presentation.AuthenticationActivity
import vn.finance.navigation.NavigationKey
import vn.finance.navigation.NavigationManager
import vn.finance.onboarding.OnboardingActivity
import javax.inject.Inject

@HiltAndroidApp
class DemoApplication : Application() {
    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate() {
        super.onCreate()
        navigationManager.registerActivity(
            NavigationKey.Onboarding(),
            OnboardingActivity::class.java.name
        )
        navigationManager.registerActivity(
            NavigationKey.Authentication(),
            AuthenticationActivity::class.java.name
        )
    }
}