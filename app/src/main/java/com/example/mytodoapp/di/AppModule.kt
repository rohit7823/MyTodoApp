package com.example.mytodoapp.di

import com.example.common.TextProvider
import com.example.domain.repository.LoginScreenRepository
import com.example.domain.repository.Preference
import com.example.domain.repository.TodoDetailsRepository
import com.example.domain.repository.TodoListScreenRepository
import com.example.domain.repository.remote_service.LoginUserApi
import com.example.domain.repository.remote_service.RegisterUserApi
import com.example.domain.repository.remote_service.TodoDetailsApi
import com.example.domain.repository.remote_service.TodoListApi
import com.example.mytodoapp.data.remote.LoginUserApiImpl
import com.example.mytodoapp.data.remote.RegisterUserApiImpl
import com.example.mytodoapp.data.remote.TodoDetailsApiImpl
import com.example.mytodoapp.data.remote.TodoListApiImpl
import com.example.mytodoapp.data.repo.*
import com.example.mytodoapp.util.Constants
import com.example.mytodoapp.util.Metar
import dagger.Module
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

@Module
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
    fun provideAndroidClient(): HttpClientConfig<AndroidEngineConfig> {
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
    fun providePreference(): Preference {
        return PreferenceImpl()
    }


    @Provides
    @Singleton
    fun provideTextProviderImpl(): TextProvider {
        return TextProviderImpl()
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

    @Provides
    @Singleton
    fun loginScreenRepositoryImpl(): LoginScreenRepository {
        return LoginScreenRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideTodoListApi(client: HttpClient): TodoListApi {
        return TodoListApiImpl(client)
    }

    @Provides
    @Singleton
    fun provideTodoListRepositoryImpl(todoListApi: TodoListApi): TodoListScreenRepository {
        return TodoListRepositoryImpl(todoListApi)
    }


    @Provides
    @Singleton
    fun provideTodoDetailsApi(client: HttpClient): TodoDetailsApi {
        return TodoDetailsApiImpl(client)
    }

    @Provides
    @Singleton
    fun provideTodoDetailsRepositoryImpl(todoDetailApi: TodoDetailsApi): TodoDetailsRepository {
        return TodoDetailsRepositoryImpl(todoDetailApi)
    }


}