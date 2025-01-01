package finance.authentication.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import vn.finance.authentication.business.domain.repository.FpEmailRepository
import vn.finance.authentication.business.domain.repository.FpNewPasswordRepository
import vn.finance.authentication.business.domain.repository.FpPinRepository
import vn.finance.authentication.business.domain.repository.LoginRepository
import vn.finance.authentication.business.domain.repository.SignupRepository
import vn.finance.authentication.business.domain.usecase.FpEmailUseCase
import vn.finance.authentication.business.domain.usecase.FpNewPasswordUseCase
import vn.finance.authentication.business.domain.usecase.FpPinUseCase
import vn.finance.authentication.business.domain.usecase.GetLoggedInUseCase
import vn.finance.authentication.business.domain.usecase.LoginUseCase
import vn.finance.authentication.business.domain.usecase.SignupUseCase

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

    @Provides
    fun providesGetLoggedInUseCase(repository: LoginRepository): GetLoggedInUseCase =
        GetLoggedInUseCase(repository = repository)
}