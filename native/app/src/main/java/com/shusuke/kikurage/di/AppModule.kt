package com.shusuke.kikurage.di

import com.shusuke.kikurage.repository.KikurageStateRepository
import com.shusuke.kikurage.repository.KikurageStateRepositoryInterface
import com.shusuke.kikurage.repository.KikurageUserRepository
import com.shusuke.kikurage.repository.KikurageUserRepositoryInterface
import com.shusuke.kikurage.repository.LoginRepository
import com.shusuke.kikurage.repository.LoginRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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
