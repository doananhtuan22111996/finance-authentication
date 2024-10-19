package vn.finance.authentication.business.data.model

import vn.finance.authentication.business.data.Configs.EMPTY_STRING
import vn.finance.authentication.business.domain.model.TokenModel
import vn.core.data.model.BaseRaw
import vn.core.domain.BaseModel

data class TokenRaw(val token: String? = EMPTY_STRING, val refreshToken: String? = EMPTY_STRING) :
    BaseRaw() {

    override fun raw2Model(): BaseModel =
        TokenModel(token = token ?: EMPTY_STRING, refreshToken = refreshToken ?: EMPTY_STRING)
}