package finance.authentication.data.repository

import finance.authentication.data.ApiService
import finance.authentication.data.Config
import finance.authentication.data.Config.EMPTY_STRING
import finance.authentication.data.model.TokenRaw
import finance.authentication.domain.model.TokenModel
import finance.authentication.domain.repository.LoginRepository
import finance.authentication.domain.repository.SignupRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import vn.core.data.di.AnoRetrofitApiService
import vn.core.data.local.PreferenceWrapper
import vn.core.data.model.ObjectResponse
import vn.core.data.network.NetworkBoundService
import vn.core.domain.ResultModel

import javax.inject.Inject

class SignupRepositoryImpl @Inject constructor(
    @AnoRetrofitApiService private val apiService: ApiService,
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
