package com.shusuke.kikurage.entity.response

import kotlinx.serialization.Serializable

@Serializable
data class LoginUser (
    val uid: String,
    val isEmailVerified: Boolean
)