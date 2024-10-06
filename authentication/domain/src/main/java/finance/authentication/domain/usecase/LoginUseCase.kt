package finance.authentication.domain.usecase

import finance.authentication.domain.Config.EMPTY_STRING
import kotlinx.coroutines.flow.Flow
import vn.core.domain.ResultModel
import vn.core.usecase.BaseUseCase
import finance.authentication.domain.model.TokenModel
import finance.authentication.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private var repository: LoginRepository) :
    BaseUseCase<String, ResultModel<TokenModel>>() {
    override fun execute(vararg params: String?): Flow<ResultModel<TokenModel>> {
        return repository.login(params.first() ?: EMPTY_STRING, params[1] ?: EMPTY_STRING)
    }
}