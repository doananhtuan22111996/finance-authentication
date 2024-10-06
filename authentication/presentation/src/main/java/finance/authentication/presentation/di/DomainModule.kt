package finance.authentication.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import finance.authentication.domain.repository.FpEmailRepository
import finance.authentication.domain.repository.FpNewPasswordRepository
import finance.authentication.domain.repository.FpPinRepository
import finance.authentication.domain.repository.LoginRepository
import finance.authentication.domain.repository.SignupRepository
import finance.authentication.domain.usecase.FpEmailUseCase
import finance.authentication.domain.usecase.FpNewPasswordUseCase
import finance.authentication.domain.usecase.FpPinUseCase
import finance.authentication.domain.usecase.LoginUseCase
import finance.authentication.domain.usecase.SignupUseCase

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun providesLoginUseCase(repository: LoginRepository): LoginUseCase =
        LoginUseCase(repository = repository)

    @Provides
    fun providesFpEmailUseCase(repository: FpEmailRepository): FpEmailUseCase =
        FpEmailUseCase(repository = repository)

    @Provides
    fun providesFpPinUseCase(repository: FpPinRepository): FpPinUseCase =
        FpPinUseCase(repository = repository)

    @Provides
    fun providesFpNewPasswordUseCase(repository: FpNewPasswordRepository): FpNewPasswordUseCase =
        FpNewPasswordUseCase(repository = repository)

    @Provides
    fun providesSignupUseCase(repository: SignupRepository): SignupUseCase =
        SignupUseCase(repository = repository)
}