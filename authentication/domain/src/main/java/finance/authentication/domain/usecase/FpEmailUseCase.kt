package finance.authentication.domain.usecase

import finance.authentication.domain.Config.EMPTY_STRING
import finance.authentication.domain.repository.FpEmailRepository
import kotlinx.coroutines.flow.Flow
import vn.core.domain.ResultModel
import vn.core.usecase.BaseUseCase
import javax.inject.Inject

class FpEmailUseCase @Inject constructor(private var repository: FpEmailRepository) :
    BaseUseCase<String, ResultModel<Nothing>>() {
    override fun execute(vararg params: String?): Flow<ResultModel<Nothing>> {
        return repository.fpEmail(email = params.first() ?: EMPTY_STRING)
    }
}