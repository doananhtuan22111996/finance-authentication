package finance.authentication.domain.repository

import kotlinx.coroutines.flow.Flow
import vn.core.domain.ResultModel

interface FpPinRepository {

    fun fpPin(pin: String): Flow<ResultModel<Nothing>>
}