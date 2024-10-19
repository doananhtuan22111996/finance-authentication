package vn.finance.authentication.business.domain.repository

import kotlinx.coroutines.flow.Flow
import vn.core.domain.ResultModel

interface FpEmailRepository {

    fun fpEmail(email: String): Flow<ResultModel<Nothing>>
}