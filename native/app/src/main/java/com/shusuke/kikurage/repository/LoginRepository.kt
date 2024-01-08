package com.shusuke.kikurage.repository

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.shusuke.kikurage.entity.response.LoginUser
import com.shusuke.kikurage.utility.helper.LoginHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

interface LoginRepositoryInterface {
    suspend fun login(email: String, password: String) : Result<LoginUser, Error>
}

class LoginRepository @Inject constructor(
    private val loginHelper: LoginHelper
) : LoginRepositoryInterface {
    private val _auth = Firebase.auth

    override suspend fun login(email: String, password: String) : Result<LoginUser, Error> {
        return withContext(Dispatchers.IO) {
            val authResult = auth(email, password)
            return@withContext if (authResult != null) {
                val user = _auth.currentUser
                user?.let {
                    val loginUser = LoginUser(uid = it.uid, isEmailVerified = it.isEmailVerified)
                    loginHelper.setLoginUserInDataStore(loginUser)
                    Ok(loginUser)
                } ?: Err(Error())
            } else {
                Err(Error())
            }
        }
    }

    private suspend fun auth(email: String, password: String) : AuthResult? {
        return try {
            val result = _auth.signInWithEmailAndPassword(email, password).await()
            result
        } catch (e: Exception) {
            null
        }
    }
}