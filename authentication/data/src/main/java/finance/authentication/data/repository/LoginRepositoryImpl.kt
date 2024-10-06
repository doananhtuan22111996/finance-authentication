package finance.authentication.data.repository

import finance.authentication.data.ApiService
import finance.authentication.data.Config
import finance.authentication.data.Config.EMPTY_STRING
import finance.authentication.data.model.TokenRaw
import finance.authentication.domain.model.TokenModel
import finance.authentication.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import vn.core.data.di.AnoRetrofitApiService
import vn.core.data.local.PreferenceWrapper
import vn.core.data.model.ObjectResponse
import vn.core.data.network.NetworkBoundService
import vn.core.domain.ResultModel

import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    @AnoRetrofitApiService private val apiService: ApiService,
    private val preferenceWrapper: PreferenceWrapper
) : LoginRepository {
    override fun login(email: String, password: String): Flow<ResultModel<TokenModel>> =
        object : NetworkBoundService<TokenRaw, TokenModel>() {
            override suspend fun onApi(): Response<ObjectResponse<TokenRaw>> =
                apiService.login(email, password)

            override suspend fun processResponse(request: ObjectResponse<TokenRaw>?): ResultModel.Success<TokenModel> {
                if (request?.data != null) {
                    preferenceWrapper.saveString(
                        Config.SharePreference.KEY_AUTH_TOKEN, request.data?.token ?: EMPTY_STRING
                    )
                    preferenceWrapper.saveString(
                        Config.SharePreference.KEY_AUTH_REFRESH_TOKEN,
                        request.data?.refreshToken ?: EMPTY_STRING
                    )
                }
                return ResultModel.Success(data = request?.data?.raw2Model() as? TokenModel)
            }
        }.build()
}
