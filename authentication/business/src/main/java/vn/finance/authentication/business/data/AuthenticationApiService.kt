package vn.finance.authentication.business.data

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query
import vn.core.data.model.ObjectResponse
import vn.core.provider.finance.model.TokenRaw

interface AuthenticationApiService {

    @POST("/login")
    suspend fun login(
        @Query("email") email: String, @Query("password") password: String
    ): Response<ObjectResponse<TokenRaw>>

    @POST("/forgot-password")
    suspend fun fpEmail(@Query("email") email: String): Response<ObjectResponse<Nothing>>

    @POST("/forgot-password/verify-pin")
    suspend fun fpPin(@Query("pin") pin: String): Response<ObjectResponse<Nothing>>

    @POST("/forgot-password/new-password")
    suspend fun fpNewPassword(
        @Query("password") password: String, @Query("confirmPassword") confirmPassword: String
    ): Response<ObjectResponse<Nothing>>

    @POST("/sign-up")
    suspend fun signup(
        @Query("username") username: String,
        @Query("email") email: String,
        @Query("phone") phone: String,
        @Query("dob") dob: String,
        @Query("password") password: String,
        @Query("confirmPassword") confirmPassword: String
    ): Response<ObjectResponse<Nothing>>
}
