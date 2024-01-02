package com.shusuke.kikurage.screen.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.mapBoth
import com.shusuke.kikurage.repository.LoginRepository
import com.shusuke.kikurage.repository.LoginRepositoryInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
                    success = { Log.d("test", "success to login") },
                    failure = { Log.d("test", "fail to login") }
                )
        }

    }
}

data class LoginUiState(
    val isLogin: Boolean = false
)