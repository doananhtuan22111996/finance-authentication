package finance.authentication.presentation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import vn.finance.authentication.business.data.repository.FpEmailRepositoryImpl
import vn.finance.authentication.business.data.repository.FpNewPasswordRepositoryImpl
import vn.finance.authentication.business.data.repository.FpPinRepositoryImpl
import vn.finance.authentication.business.data.repository.LoginRepositoryImpl
import vn.finance.authentication.business.data.repository.SignupRepositoryImpl
import vn.finance.authentication.business.domain.repository.FpEmailRepository
import vn.finance.authentication.business.domain.repository.FpNewPasswordRepository
import vn.finance.authentication.business.domain.repository.FpPinRepository
import vn.finance.authentication.business.domain.repository.LoginRepository
import vn.finance.authentication.business.domain.repository.SignupRepository

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


