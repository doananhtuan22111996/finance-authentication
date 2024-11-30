package vn.finance.authentication.di

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import vn.core.composex.uikit.Container
import vn.finance.onboarding.api.OnboardingApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DemoModule {

    @Provides
    @Singleton
    fun provideFakeOnboardingApi(): OnboardingApi = object : OnboardingApi {
        override val path: String
            get() = "onboarding"

        @Composable
        override fun OnboardingPage(navigateTo: () -> Unit) {
            Container(appBarTitle = "Onboarding") { innerPadding ->
                Box(modifier = Modifier.fillMaxSize()) {
                    Button(
                        modifier = Modifier.align(Alignment.Center), onClick = navigateTo
                    ) {
                        Text("Go to Authentication")
                    }
                }
            }
        }
    }
}