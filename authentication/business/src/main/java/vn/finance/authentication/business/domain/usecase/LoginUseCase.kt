package vn.finance.authentication.business.domain.usecase

import kotlinx.coroutines.flow.Flow
import vn.core.domain.ResultModel
import vn.core.provider.finance.model.TokenModel
import vn.core.usecase.BaseUseCase
import vn.finance.authentication.business.domain.Configs.EMPTY_STRING
import vn.finance.authentication.business.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private var repository: LoginRepository) :
    BaseUseCase<String, ResultModel<TokenModel>>() {
    override fun execute(vararg params: String?): Flow<ResultModel<TokenModel>> {
        return repository.login(params.first() ?: EMPTY_STRING, params[1] ?: EMPTY_STRING)
    }
}