package com.example.ankiscan.anki

import androidx.datastore.preferences.preferencesDataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey

const val PREFERENCES_NAME = "anki"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(PREFERENCES_NAME)

class AnkiData(private val context: Context) {
    suspend fun <T> writeToDataStore(key: String, value: T) {
        val preferencesKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] =
        }
    }
}