package vn.finance.authentication.business.data.repository

import vn.finance.authentication.business.data.AuthenticationApiService
import vn.finance.authentication.business.data.Configs.EMPTY_STRING
import vn.finance.authentication.business.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import vn.core.data.di.AnoRetrofitApiService
import vn.core.data.local.PreferenceWrapper
import vn.core.data.model.ObjectResponse
import vn.core.data.network.NetworkBoundService
import vn.core.domain.ResultModel
import vn.core.domain.TypeException
import vn.core.provider.finance.Configs
import vn.core.provider.finance.model.TokenModel
import vn.core.provider.finance.model.TokenRaw

import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    @AnoRetrofitApiService private val apiService: AuthenticationApiService,
    private val preferenceWrapper: PreferenceWrapper
) : LoginRepository {
    override fun login(email: String, password: String): Flow<ResultModel<TokenModel>> =
        object : NetworkBoundService<TokenRaw, TokenModel>() {
            override suspend fun onApi(): Response<ObjectResponse<TokenRaw>> =
                apiService.login(email, password)

            override suspend fun processResponse(request: ObjectResponse<TokenRaw>?): ResultModel.Success<TokenModel> {
                if (request?.data != null) {
                    preferenceWrapper.saveString(
                        Configs.SharePreference.KEY_AUTH_TOKEN, request.data?.token ?: EMPTY_STRING
                    )
                    preferenceWrapper.saveString(
                        Configs.SharePreference.KEY_AUTH_REFRESH_TOKEN,
                        request.data?.refreshToken ?: EMPTY_STRING
                    )
                }
                return ResultModel.Success(data = request?.data?.raw2Model() as? TokenModel)
            }
        }.build()

    override fun getLoggedIn(): Flow<ResultModel<Boolean>> = flow {
        try {
            val token =
                preferenceWrapper.getString(Configs.SharePreference.KEY_AUTH_TOKEN, EMPTY_STRING)
            ResultModel.Success(data = token.isNotEmpty())
        } catch (e: Exception) {
            ResultModel.AppException(type = TypeException.Local, message = e.message)
        }
    }
}
