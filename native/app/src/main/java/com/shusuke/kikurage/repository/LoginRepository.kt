package com.shusuke.kikurage.repository

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.shusuke.kikurage.entity.response.LoginUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface LoginRepositoryInterface {
    suspend fun login(email: String, password: String) : Result<LoginUser, Error>
}

class LoginRepository : LoginRepositoryInterface {
    private val _auth = Firebase.auth

    override suspend fun login(email: String, password: String) : Result<LoginUser, Error> {
        return withContext(Dispatchers.IO) {
            val res = _auth.signInWithEmailAndPassword(email, password)
            return@withContext if (res.isSuccessful) {
                val user = _auth.currentUser
                user?.let {
                    Ok(LoginUser(uid = user.uid, isEmailVerified = user.isEmailVerified))
                } ?: Err(Error())
            } else {
                Err(Error())
            }
        }
    }
}