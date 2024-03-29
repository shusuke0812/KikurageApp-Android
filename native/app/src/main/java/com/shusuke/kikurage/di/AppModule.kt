package com.shusuke.kikurage.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.shusuke.kikurage.LoginUserPrefs
import com.shusuke.kikurage.constant.Constants
import com.shusuke.kikurage.repository.KikurageStateRepository
import com.shusuke.kikurage.repository.KikurageStateRepositoryInterface
import com.shusuke.kikurage.repository.KikurageUserRepository
import com.shusuke.kikurage.repository.KikurageUserRepositoryInterface
import com.shusuke.kikurage.repository.LoginRepository
import com.shusuke.kikurage.repository.LoginRepositoryInterface
import com.shusuke.kikurage.usecase.LoadKikurageStateWithUserUseCase
import com.shusuke.kikurage.usecase.LoadKikurageStateWithUserUseCaseInterface
import com.shusuke.kikurage.utility.LoginUserPrefsSerializer
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideLoginUserDataStore(
        @ApplicationContext context: Context,
        loginUserPrefsSerializer: LoginUserPrefsSerializer
    ): DataStore<LoginUserPrefs> {
        return DataStoreFactory.create(
            serializer = loginUserPrefsSerializer,
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
        ) {
            context.dataStoreFile(Constants.ProtoDataStore.LOGIN_USER_FILE_NAME)
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindLoginRepositoryInterface(loginRepository: LoginRepository): LoginRepositoryInterface

    @Singleton
    @Binds
    abstract fun bindKikurageUserRepositoryInterface(kikurageUserRepository: KikurageUserRepository): KikurageUserRepositoryInterface

    @Singleton
    @Binds
    abstract fun bindKikurageStateRepositoryInterface(kikurageStateRepository: KikurageStateRepository): KikurageStateRepositoryInterface
}

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Singleton
    @Binds
    abstract fun loadKikurageStateWithUserUseCaseInterface(loadKikurageStateWithUserUseCase : LoadKikurageStateWithUserUseCase) : LoadKikurageStateWithUserUseCaseInterface
}
