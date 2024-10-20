package vn.finance.authentication.business.data.repository

import vn.finance.authentication.business.data.AuthenticationApiService
import vn.finance.authentication.business.domain.repository.SignupRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import vn.core.data.di.AnoRetrofitApiService
import vn.core.data.model.ObjectResponse
import vn.core.data.network.NetworkBoundService
import vn.core.domain.ResultModel

import javax.inject.Inject

class SignupRepositoryImpl @Inject constructor(
    @AnoRetrofitApiService private val apiService: AuthenticationApiService,
) : SignupRepository {
    override fun signup(
        username: String,
        email: String,
        phone: String,
        dob: String,
        password: String,
        confirmPassword: String
    ): Flow<ResultModel<Nothing>> = object : NetworkBoundService<Nothing, Nothing>() {
        override suspend fun onApi(): Response<ObjectResponse<Nothing>> =
            apiService.signup(username, email, phone, dob, password, confirmPassword)

        override suspend fun processResponse(request: ObjectResponse<Nothing>?): ResultModel.Success<Nothing> {
            return ResultModel.Success()
        }
    }.build()
}
