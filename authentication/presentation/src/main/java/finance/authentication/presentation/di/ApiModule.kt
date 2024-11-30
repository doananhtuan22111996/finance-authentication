package finance.authentication.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import finance.authentication.presentation.api.AuthenticationApiImpl
import vn.finance.authentication.api.AuthenticationApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideAuthenticationApi(): AuthenticationApi = AuthenticationApiImpl()
}