package finance.authentication.domain.usecase

import finance.authentication.domain.Config.EMPTY_STRING
import finance.authentication.domain.repository.FpNewPasswordRepository
import kotlinx.coroutines.flow.Flow
import vn.core.domain.ResultModel
import vn.core.usecase.BaseUseCase
import javax.inject.Inject

class FpNewPasswordUseCase @Inject constructor(private var repository: FpNewPasswordRepository) :
    BaseUseCase<String, ResultModel<Nothing>>() {
    override fun execute(vararg params: String?): Flow<ResultModel<Nothing>> {
        return repository.fpNewPassword(
            password = params.first() ?: EMPTY_STRING, confirmPassword = params[1] ?: EMPTY_STRING
        )
    }
}