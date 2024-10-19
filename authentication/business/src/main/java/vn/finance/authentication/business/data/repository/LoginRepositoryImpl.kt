package vn.finance.authentication.business.data.repository

import vn.finance.authentication.business.data.ApiService
import vn.finance.authentication.business.data.Configs
import vn.finance.authentication.business.data.Configs.EMPTY_STRING
import vn.finance.authentication.business.data.model.TokenRaw
import vn.finance.authentication.business.domain.model.TokenModel
import vn.finance.authentication.business.domain.repository.LoginRepository
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
}