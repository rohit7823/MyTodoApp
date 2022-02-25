package com.example.mytodoapp.di

import com.example.domain.repository.remote_service.LoginUserApi
import com.example.domain.repository.remote_service.RegisterUserApi
import com.example.mytodoapp.data.remote.LoginUserApiImpl
import com.example.mytodoapp.data.remote.RegisterUserApiImpl
import com.example.mytodoapp.util.Constants
import com.example.mytodoapp.util.Metar
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiClient(): HttpClient {
        return HttpClient(Android) {
            provideAndroidClient()
        }
    }

    @Provides
    @Singleton
    private fun provideAndroidClient(): HttpClientConfig<AndroidEngineConfig> {
        return HttpClientConfig<AndroidEngineConfig>().apply {
            install(Logging) {
                level = LogLevel.ALL
            }

            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    prettyPrint = true
                    isLenient = false
                    ignoreUnknownKeys = true
                })
            }

            install(DefaultRequest) {
                host = Metar[Constants.BASE_URL]
                url { protocol = URLProtocol.HTTPS }
            }

            engine {
                connectTimeout = Constants.CONNECTION_TIMEOUT
                socketTimeout = Constants.CONNECTION_TIMEOUT
            }
        }
    }


    @Provides
    @Singleton
    fun provideLoginUserImpl(client: HttpClient): LoginUserApi {
        return LoginUserApiImpl(client)
    }

    @Provides
    @Singleton
    fun provideRegisterUserImpl(client: HttpClient): RegisterUserApi {
        return RegisterUserApiImpl(client)
    }
}