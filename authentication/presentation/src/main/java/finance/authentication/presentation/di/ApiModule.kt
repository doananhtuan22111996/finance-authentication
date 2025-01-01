package finance.authentication.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import finance.authentication.presentation.api.AuthenticationApiImpl
import finance.authentication.presentation.api.GetLoggedInProviderImpl
import vn.finance.authentication.api.AuthenticationApi
import vn.finance.authentication.api.GetLoggedInProvider
import vn.finance.authentication.business.domain.usecase.GetLoggedInUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideAuthenticationApi(): AuthenticationApi = AuthenticationApiImpl()
}

@Module
@InstallIn(ViewModelComponent::class)
class ApiViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideGetLoggedInProvider(useCase: GetLoggedInUseCase): GetLoggedInProvider =
        GetLoggedInProviderImpl(useCase)
}