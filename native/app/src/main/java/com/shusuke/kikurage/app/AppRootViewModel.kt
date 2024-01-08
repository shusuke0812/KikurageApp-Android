package com.shusuke.kikurage.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shusuke.kikurage.utility.helper.LoginHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppRootViewModel @Inject constructor(
    private val loginHelper: LoginHelper
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    init {
        viewModelScope.launch {
            loginHelper.isLogin.collect { isLogin ->
                _uiState.value = LoginUiState(isLogin)
            }
        }
    }

    data class LoginUiState(
        val isLogin: Boolean = false,
    )
}

