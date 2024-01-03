package com.shusuke.kikurage.entity.response

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.PropertyName
import java.util.Date

// NOTE: To avoid decode exception, have to set up default values

data class KikurageUser(
    @PropertyName("productKey") val productKey: String = "",
    @PropertyName("kikurageName") val kikurageName: String = "",
    @PropertyName("cultivationStartDate") val cultivationStartDate: Date = Date(),
    @PropertyName("stateRef") val stateRef: DocumentReference? = null,
    @PropertyName("createdAt") val createdAt: Date = Date(System.currentTimeMillis()),
    @PropertyName("updatedAt") val updatedAt: Date = Date(System.currentTimeMillis())
)