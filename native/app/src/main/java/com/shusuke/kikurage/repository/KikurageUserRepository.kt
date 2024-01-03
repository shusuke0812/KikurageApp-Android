package com.shusuke.kikurage.repository

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import javax.inject.Inject
import com.github.michaelbull.result.Result
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.shusuke.kikurage.constant.Constants
import com.shusuke.kikurage.entity.response.KikurageUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Error

interface KikurageUserRepositoryInterface {
    suspend fun getKikurageUser(uid: String): Result<KikurageUser, Error>
}

class KikurageUserRepository @Inject constructor() : KikurageUserRepositoryInterface {
    private val _firestore = Firebase.firestore

    override suspend fun getKikurageUser(uid: String): Result<KikurageUser, Error> {
        return withContext(Dispatchers.IO) {
            val querySnapshot = try {
                _firestore.collection(Constants.FirestoreCollectionName.USERS)
                    .document(uid)
                    .get()
                    .await()
            } catch (e: FirebaseFirestoreException) {
                Err(e)
            }
            val res = (querySnapshot as? DocumentSnapshot)?.toObject(KikurageUser::class.java)
            res?.let { Ok(res) } ?: Err(Error())
        }
    }
}