package com.shusuke.kikurage.utility

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.shusuke.kikurage.LoginUserPrefs
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class LoginUserPrefsSerializer @Inject constructor() : Serializer<LoginUserPrefs> {
    override val defaultValue: LoginUserPrefs
        get() = LoginUserPrefs.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): LoginUserPrefs {
        return try {
            LoginUserPrefs.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto", e)
        }
    }

    override suspend fun writeTo(t: LoginUserPrefs, output: OutputStream) {
        t.writeTo(output)
    }
}
