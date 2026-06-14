package com.example.proyek_akhir_kewirausahaan.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {

    companion object {
        private val KEY_NAME = stringPreferencesKey("user_name")
        private val KEY_AVATAR_URI = stringPreferencesKey("user_avatar_uri")
        private val KEY_AVATAR_LOOK = stringPreferencesKey("avatar_look")
    }

    val userName: Flow<String?> = context.dataStore.data.map { it[KEY_NAME] }
    val avatarUri: Flow<String?> = context.dataStore.data.map { it[KEY_AVATAR_URI] }
    val avatarLook: Flow<String?> = context.dataStore.data.map { it[KEY_AVATAR_LOOK] }

    suspend fun setUserName(name: String) {
        context.dataStore.edit { it[KEY_NAME] = name }
    }

    suspend fun setAvatarUri(uri: String) {
        context.dataStore.edit { it[KEY_AVATAR_URI] = uri }
    }

    suspend fun setAvatarLook(json: String) {
        context.dataStore.edit { it[KEY_AVATAR_LOOK] = json }
    }
}