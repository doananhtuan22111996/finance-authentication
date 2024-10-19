package vn.finance.authentication.business.domain.repository

import kotlinx.coroutines.flow.Flow
import vn.core.domain.ResultModel
import vn.finance.authentication.business.domain.model.TokenModel

interface LoginRepository {

    fun login(email: String, password: String): Flow<ResultModel<TokenModel>>
}