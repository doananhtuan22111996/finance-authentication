package finance.authentication.presentation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import finance.authentication.data.repository.FpEmailRepositoryImpl
import finance.authentication.data.repository.FpNewPasswordRepositoryImpl
import finance.authentication.data.repository.FpPinRepositoryImpl
import finance.authentication.data.repository.LoginRepositoryImpl
import finance.authentication.data.repository.SignupRepositoryImpl
import finance.authentication.domain.repository.FpEmailRepository
import finance.authentication.domain.repository.FpNewPasswordRepository
import finance.authentication.domain.repository.FpPinRepository
import finance.authentication.domain.repository.LoginRepository
import finance.authentication.domain.repository.SignupRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindsLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Binds
    abstract fun bindsFpEmailRepository(fpEmailRepositoryImpl: FpEmailRepositoryImpl): FpEmailRepository

    @Binds
    abstract fun bindsFpPinRepository(fpPinRepositoryImpl: FpPinRepositoryImpl): FpPinRepository

    @Binds
    abstract fun bindsFpNewPasswordRepository(fpNewPasswordRepositoryImpl: FpNewPasswordRepositoryImpl): FpNewPasswordRepository

    @Binds
    abstract fun bindsSignupRepository(signupRepositoryImpl: SignupRepositoryImpl): SignupRepository
}


