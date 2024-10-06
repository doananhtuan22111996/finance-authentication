package finance.authentication.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import finance.authentication.data.ApiService
import finance.authentication.data.BuildConfig
import finance.authentication.data.Config
import finance.authentication.data.network.HttpAuthenticatorInterceptor
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import vn.core.data.di.AnoHttpAuthenticatorInterceptor
import vn.core.data.di.AnoHttpLoggingInterceptor
import vn.core.data.di.AnoRetrofitApiService
import vn.core.data.di.ProviderModule.provideRetrofit
import vn.core.data.local.PreferenceWrapper
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule {

    @Provides
    @Singleton
    @AnoRetrofitApiService
    fun provideApiServices(
        okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
    ): ApiService {
        return provideRetrofit<ApiService>(
            baseUrl = Config.MAIN_DOMAIN,
            okHttpClient = okHttpClient,
            gsonConverterFactory = gsonConverterFactory
        )
    }

    @Provides
    @Singleton
    @AnoHttpAuthenticatorInterceptor
    fun bindHttpAuthenticatorInterceptor(preferenceWrapper: PreferenceWrapper): HttpAuthenticatorInterceptor {
        return HttpAuthenticatorInterceptor(preferenceWrapper)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @AnoHttpLoggingInterceptor httpLoggingInterceptor: HttpLoggingInterceptor,
        @AnoHttpAuthenticatorInterceptor httpAuthenticatorInterceptor: HttpAuthenticatorInterceptor,
        preferenceWrapper: PreferenceWrapper,
        cache: Cache,
    ): OkHttpClient {
        val builder =
            OkHttpClient.Builder().readTimeout(Config.TimeOut.TIMEOUT_READ_SECOND, TimeUnit.SECONDS)
                .connectTimeout(Config.TimeOut.TIMEOUT_CONNECT_SECOND, TimeUnit.SECONDS)
                .writeTimeout(Config.TimeOut.TIMEOUT_WRITE_SECOND, TimeUnit.SECONDS)
                .addNetworkInterceptor(
                    Interceptor { chain ->
                        var request = chain.request()
                        val builder = request.newBuilder()
                        val token =
                            preferenceWrapper.getString(Config.SharePreference.KEY_AUTH_TOKEN, "")
                        if (token.isNotEmpty()) {
                            builder.addHeader("Authorization", "Bearer $token")
                        }
                        request = builder.build()
                        chain.proceed(request)
                    },
                ).authenticator(httpAuthenticatorInterceptor)
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(httpLoggingInterceptor)
        } else {
            builder.cache(cache)
        }
        return builder.build()
    }
}