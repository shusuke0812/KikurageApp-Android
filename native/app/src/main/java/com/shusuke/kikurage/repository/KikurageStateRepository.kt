package com.shusuke.kikurage.repository

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.github.michaelbull.result.Result
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreException
import com.shusuke.kikurage.constant.Constants
import com.shusuke.kikurage.entity.response.KikurageState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

interface KikurageStateRepositoryInterface {
    suspend fun getKikurageState(productId: String): Result<KikurageState, Error>
}

class KikurageStateRepository : KikurageStateRepositoryInterface {
    private val _firestore = Firebase.firestore

    override suspend fun getKikurageState(productId: String):  Result<KikurageState, Error> {
        return  withContext(Dispatchers.IO) {
            val querySnapshot = try {
                _firestore.collection(Constants.FirestoreCollectionName.STATES)
                    .document(productId)
                    .get()
                    .await()
            } catch (e: FirebaseFirestoreException) {
                Err(e)
            }
            val res = (querySnapshot as? DocumentSnapshot)?.toObject(KikurageState::class.java)
            res?.let { Ok(res) } ?: Err(Error())
        }
    }
}