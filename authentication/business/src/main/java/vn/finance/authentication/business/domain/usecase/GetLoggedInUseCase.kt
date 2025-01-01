package vn.finance.authentication.business.domain.usecase

import kotlinx.coroutines.flow.Flow
import vn.core.domain.ResultModel
import vn.core.usecase.BaseUseCase
import vn.finance.authentication.business.domain.repository.LoginRepository
import javax.inject.Inject

class GetLoggedInUseCase @Inject constructor(private val repository: LoginRepository) :
    BaseUseCase<Unit, ResultModel<Boolean>>() {
    override fun execute(vararg params: Unit?): Flow<ResultModel<Boolean>> {
        return repository.getLoggedIn()
    }
}