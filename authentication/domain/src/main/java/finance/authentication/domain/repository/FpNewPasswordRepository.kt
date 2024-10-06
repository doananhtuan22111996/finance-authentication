package finance.authentication.domain.repository

import kotlinx.coroutines.flow.Flow
import vn.core.domain.ResultModel

interface FpNewPasswordRepository {

    fun fpNewPassword(password: String, confirmPassword: String): Flow<ResultModel<Nothing>>
}