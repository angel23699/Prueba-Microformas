package com.example.microformastest.domain.DataStorage_plus_Tink

import android.content.Context
import android.util.Log
import com.google.crypto.tink.Aead
import com.google.crypto.tink.KeysetHandle
import com.google.crypto.tink.aead.AeadKeyTemplates
import com.google.crypto.tink.config.TinkConfig
import com.google.crypto.tink.integration.android.AndroidKeysetManager
import java.security.GeneralSecurityException

class TokenEncryptor(private val context: Context) {

    companion object {
        init {
            try {
                TinkConfig.register()
            } catch (e: GeneralSecurityException) {
                Log.e("TokenEncryptor", "Failed to register Tink", e)
                throw RuntimeException("Failed to initialize Tink", e)
            }
        }
    }

    private val keysetHandle: KeysetHandle by lazy {
        AndroidKeysetManager.Builder()
            .withKeyTemplate(AeadKeyTemplates.AES256_GCM)
            .withSharedPref(context, "tink_keyset", "token_keyset_name")
            .withMasterKeyUri("android-keystore://tink_master_key")
            .build()
            .keysetHandle
    }

    private val aead: Aead by lazy {
        keysetHandle.getPrimitive(Aead::class.java)
    }

    fun encrypt(token: String): ByteArray {
        return aead.encrypt(token.toByteArray(Charsets.UTF_8), null)
    }

    fun decrypt(encryptedToken: ByteArray): String {
        val decryptedBytes = aead.decrypt(encryptedToken, null)
        return String(decryptedBytes, Charsets.UTF_8)
    }
}
