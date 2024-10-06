package finance.authentication.data.model

import finance.authentication.data.Config.EMPTY_STRING
import finance.authentication.domain.model.TokenModel
import vn.core.data.model.BaseRaw
import vn.core.domain.BaseModel

data class TokenRaw(val token: String? = EMPTY_STRING, val refreshToken: String? = EMPTY_STRING) :
    BaseRaw() {

    override fun raw2Model(): BaseModel =
        TokenModel(token = token ?: EMPTY_STRING, refreshToken = refreshToken ?: EMPTY_STRING)
}