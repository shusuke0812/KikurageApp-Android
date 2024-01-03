package com.shusuke.kikurage.entity.response

import com.google.firebase.firestore.PropertyName

// NOTE: To avoid decode exception, have to set up default values

data class KikurageState (
    @PropertyName("temperature") val temperature: Int = 0,
    @PropertyName("humidity") val humidity: Int = 0,
    @PropertyName("message") val message: String = "",
    @PropertyName("judge") val typeString: String = "",
    @PropertyName("advice") val advice: String = ""
)