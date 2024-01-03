package com.shusuke.kikurage.entity.response

data class KikurageState (
    val temperature: Int,
    val humidity: Int,
    val message: String,
    val typeString: String,
    val advice: String
)