package com.shusuke.kikurage.utility.helper

import androidx.datastore.core.DataStore
import com.shusuke.kikurage.LoginUserPrefs
import com.shusuke.kikurage.entity.response.LoginUser
import com.shusuke.kikurage.utility.CustomTimber
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginHelper @Inject constructor(
    private val dataStore: DataStore<LoginUserPrefs>
) {

    private fun readUserFromDataStore(): Flow<LoginUser> {
        return dataStore.data
            .map { LoginUser(uid = it.uid, isEmailVerified = it.isEmailVerified) }
            .catch { exception ->
                if (exception is IOException) {
                    emit(LoginUser())
                } else {
                    throw exception
                }
            }
    }

    suspend fun setLoginUserInDataStore(user: LoginUser) {
        try {
            dataStore.updateData {
                it.toBuilder().setUid(user.uid).build()
                it.toBuilder().setIsEmailVerified(user.isEmailVerified).build()
            }
        } catch (ioException: IOException) {
            CustomTimber.d("Failed to update login user prefs, $ioException")
        }
    }

    val isLogin: Flow<Boolean> = readUserFromDataStore().map { it -> it.isEmailVerified }

    suspend fun removeUserFromDtaStore() {
        dataStore.updateData {
            it.toBuilder().clear().build()
        }
    }
}
