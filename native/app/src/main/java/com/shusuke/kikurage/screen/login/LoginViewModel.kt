package com.shusuke.kikurage.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.mapBoth
import com.shusuke.kikurage.repository.LoginRepository
import com.shusuke.kikurage.repository.LoginRepositoryInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepositoryInterface = LoginRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginRepository.login(email, password)
                .mapBoth(
                    success = { _uiState.update { currentState -> currentState.copy(isLogin = true, error = null) } },
                    failure = { _uiState.update { currentState -> currentState.copy(isLogin = false, error = LoginError.LOGIN_FAILURE) } }
                )
        }
    }

    private fun validateLogin() {
        // TODO: validate email and password inputs
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
