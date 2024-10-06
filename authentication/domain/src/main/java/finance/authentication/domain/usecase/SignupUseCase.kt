package finance.authentication.domain.usecase

import finance.authentication.domain.Config.EMPTY_STRING
import kotlinx.coroutines.flow.Flow
import vn.core.domain.ResultModel
import vn.core.usecase.BaseUseCase
import finance.authentication.domain.model.TokenModel
import finance.authentication.domain.repository.SignupRepository
import javax.inject.Inject

class SignupUseCase @Inject constructor(private var repository: SignupRepository) :
    BaseUseCase<String, ResultModel<TokenModel>>() {
    override fun execute(vararg params: String?): Flow<ResultModel<Nothing>> {
        return repository.signup(
            params.first() ?: EMPTY_STRING,
            params[1] ?: EMPTY_STRING,
            params[2] ?: EMPTY_STRING,
            params[3] ?: EMPTY_STRING,
            params[4] ?: EMPTY_STRING,
            params[5] ?: EMPTY_STRING
        )
    }
}