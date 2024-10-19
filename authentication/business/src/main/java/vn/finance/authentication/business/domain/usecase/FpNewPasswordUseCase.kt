package vn.finance.authentication.business.domain.usecase

import vn.finance.authentication.business.domain.repository.FpNewPasswordRepository
import kotlinx.coroutines.flow.Flow
import vn.core.domain.ResultModel
import vn.core.usecase.BaseUseCase
import vn.finance.authentication.business.domain.Configs.EMPTY_STRING
import javax.inject.Inject

class FpNewPasswordUseCase @Inject constructor(private var repository: FpNewPasswordRepository) :
    BaseUseCase<String, ResultModel<Nothing>>() {
    override fun execute(vararg params: String?): Flow<ResultModel<Nothing>> {
        return repository.fpNewPassword(
            password = params.first() ?: EMPTY_STRING, confirmPassword = params[1] ?: EMPTY_STRING
        )
    }
}