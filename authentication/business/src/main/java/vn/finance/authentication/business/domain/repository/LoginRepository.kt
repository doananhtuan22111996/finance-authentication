package vn.finance.authentication.business.domain.repository

import kotlinx.coroutines.flow.Flow
import vn.core.domain.ResultModel
import vn.core.provider.finance.model.TokenModel

interface LoginRepository {

    fun login(email: String, password: String): Flow<ResultModel<TokenModel>>
}