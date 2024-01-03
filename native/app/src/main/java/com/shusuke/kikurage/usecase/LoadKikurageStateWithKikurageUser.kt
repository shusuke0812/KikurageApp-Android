package com.shusuke.kikurage.usecase

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.flatMap
import com.shusuke.kikurage.entity.response.KikurageState
import com.shusuke.kikurage.repository.KikurageStateRepositoryInterface
import com.shusuke.kikurage.repository.KikurageUserRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Error
import javax.inject.Inject

interface LoadKikurageStateWithUserUseCaseInterface {
    suspend operator fun invoke(uid: String): Result<KikurageState, Error>
}

class LoadKikurageStateWithUserUseCase @Inject constructor(
    private val kikurageUserRepository: KikurageUserRepositoryInterface,
    private val kikurageStateRepository: KikurageStateRepositoryInterface
) : LoadKikurageStateWithUserUseCaseInterface {

    override suspend operator fun invoke(uid: String): Result<KikurageState, Error> {
        return withContext(Dispatchers.Default) {
            kikurageUserRepository.getKikurageUser(uid)
                .flatMap { kikurageStateRepository.getKikurageState(it.productKey) }
        }
    }
}