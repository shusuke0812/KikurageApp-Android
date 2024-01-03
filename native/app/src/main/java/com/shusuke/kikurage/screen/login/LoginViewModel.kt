package com.shusuke.kikurage.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.flatMap
import com.github.michaelbull.result.mapBoth
import com.shusuke.kikurage.repository.LoginRepositoryInterface
import com.shusuke.kikurage.usecase.LoadKikurageStateWithUserUseCaseInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepositoryInterface,
    private val loadKikurageStateWithUserUseCase: LoadKikurageStateWithUserUseCaseInterface
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun login(email: String, password: String) {
        if (!validateLogin(email, password)) {
            return
        }
        viewModelScope.launch {
            loginRepository.login(email, password)
                .flatMap { loadKikurageStateWithUserUseCase(it.uid) }
                .mapBoth(
                    success = { _uiState.update { it.copy(isLogin = true, error = null) } },
                    failure = { _uiState.update { it.copy(isLogin = false, error = LoginError.LOGIN_FAILURE) } }
                )
        }
    }

    private fun validateLogin(email: String, password: String) : Boolean {
        return if (email.isEmpty() || password.isEmpty()) {
            _uiState.update { it.copy(isLogin = false, error = LoginError.VALIDATE_ERROR) }
            false
        } else {
            true
        }
    }
}

data class LoginUiState(
    val isLogin: Boolean = false,
    val error: LoginError? = null
)

enum class LoginError {
    VALIDATE_ERROR,
    LOGIN_FAILURE
}
