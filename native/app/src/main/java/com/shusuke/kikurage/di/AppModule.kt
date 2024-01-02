package com.shusuke.kikurage.di

import com.shusuke.kikurage.repository.LoginRepository
import com.shusuke.kikurage.repository.LoginRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Singleton
    @Binds
    abstract fun bindLoginRepositoryInterface(loginRepository: LoginRepository): LoginRepositoryInterface
}