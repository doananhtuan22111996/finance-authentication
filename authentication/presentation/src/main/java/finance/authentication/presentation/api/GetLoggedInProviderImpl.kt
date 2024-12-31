package finance.authentication.presentation.api

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import vn.core.domain.ResultModel
import vn.finance.authentication.api.GetLoggedInProvider
import vn.finance.authentication.business.domain.usecase.GetLoggedInUseCase
import javax.inject.Inject

class GetLoggedInProviderImpl @Inject constructor(private val getLoggedInUseCase: GetLoggedInUseCase) :
    GetLoggedInProvider {
    override fun getLoggedIn(): Flow<Boolean> = getLoggedInUseCase.execute(Unit).map {
        if (it is ResultModel.Success) {
            it.data ?: false
        } else {
            false
        }
    }
}