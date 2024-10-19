package vn.finance.authentication.business.domain.usecase

import vn.finance.authentication.business.domain.repository.FpPinRepository
import kotlinx.coroutines.flow.Flow
import vn.core.domain.ResultModel
import vn.core.usecase.BaseUseCase
import vn.finance.authentication.business.domain.Configs.EMPTY_STRING
import javax.inject.Inject

class FpPinUseCase @Inject constructor(private var repository: FpPinRepository) :
    BaseUseCase<String, ResultModel<Nothing>>() {
    override fun execute(vararg params: String?): Flow<ResultModel<Nothing>> {
        return repository.fpPin(pin = params.first() ?: EMPTY_STRING)
    }
}