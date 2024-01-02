package com.shusuke.kikurage.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.mapBoth
import com.shusuke.kikurage.repository.LoginRepository
import com.shusuke.kikurage.repository.LoginRepositoryInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class LoginViewModel(
    private val loginRepository: LoginRepositoryInterface = LoginRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginRepository.login(email, password)
                .mapBoth(
                    success = { Timber.d("success to login") },
                    failure = { Timber.d("fail to login") }
                )
        }

    }
}

data class LoginUiState(
    val isLogin: Boolean = false
)