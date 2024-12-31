package vn.finance.authentication.api

import kotlinx.coroutines.flow.Flow

interface GetLoggedInProvider {
    fun getLoggedIn(): Flow<Boolean>
}