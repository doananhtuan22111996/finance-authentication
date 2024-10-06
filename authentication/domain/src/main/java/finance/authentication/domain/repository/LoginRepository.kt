package finance.authentication.domain.repository

import kotlinx.coroutines.flow.Flow
import vn.core.domain.ResultModel
import finance.authentication.domain.model.TokenModel

interface LoginRepository {

    fun login(email: String, password: String): Flow<ResultModel<TokenModel>>
}