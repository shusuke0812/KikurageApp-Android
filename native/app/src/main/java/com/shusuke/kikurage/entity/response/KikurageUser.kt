package com.shusuke.kikurage.entity.response

import com.google.firebase.firestore.DocumentReference

data class KikurageUser(
    val productKey: String,
    val kikurageName: String,
    val stateRef: DocumentReference?,
    // TODO: createdAt and updatedAt properties
)