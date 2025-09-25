package com.example.microformastest.domain.DataStorage_plus_Tink

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Base64

class DataStoreManager(private val context: Context) {

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "SUPER_SUPER_SECURE_PREFS")

    private val tokenEncryptor = TokenEncryptor(context)

    suspend fun putString(key: String, value: String) {
        val dataStoreKey = stringPreferencesKey(key)
        val encryptedToken = tokenEncryptor.encrypt(value)
        val encodedToken = Base64.getEncoder().encodeToString(encryptedToken)
        context.dataStore.edit { preferences ->
            preferences[dataStoreKey] = encodedToken
        }
    }

    fun getString(key: String): Flow<String?> {
        val dataStoreKey = stringPreferencesKey(key)
        return context.dataStore.data.map { preferences ->
            val encodedToken = preferences[dataStoreKey] ?: return@map null
            val encryptedToken = Base64.getDecoder().decode(encodedToken)
            tokenEncryptor.decrypt(encryptedToken)
        }
    }

    suspend fun clearString(key: String) {
        val dataStoreKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences.remove(dataStoreKey)
        }
    }
}