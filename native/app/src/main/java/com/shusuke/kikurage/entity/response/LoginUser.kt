package com.shusuke.kikurage.entity.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginUser (
    @Json(name = "uid") val uid: String = "",
    @Json(name = "is_email_verified") val isEmailVerified: Boolean = false
)