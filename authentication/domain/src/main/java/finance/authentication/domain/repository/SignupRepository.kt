package finance.authentication.domain.repository

import kotlinx.coroutines.flow.Flow
import vn.core.domain.ResultModel

interface SignupRepository {

    fun signup(
        username: String,
        email: String,
        phone: String,
        dob: String,
        password: String,
        confirmPassword: String
    ): Flow<ResultModel<Nothing>>
}