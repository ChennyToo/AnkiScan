package com.example.ankiscan.anki

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ichi2.anki.api.AddContentApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AnkiDroidHelper(
    private val context: Context
) {
    private val applicationContext: Context = context.applicationContext
    private val api: AddContentApi = AddContentApi(applicationContext)

    // This object allows us to store data such as deck ID and model ID
    private val Context.ankiData: DataStore<Preferences> by preferencesDataStore(name = "anki")
    private val DECK_KEY: String = "deck"
    private val MODEL_KEY: String = "model"

    companion object {
        /**
         * Whether or not the API is available to use.
         * The API could be unavailable if AnkiDroid is not installed or the user explicitly disabled the API
         * @return true if the API is available to use
         */
        public fun isApiAvailable(context: Context): Boolean {
            return AddContentApi.getAnkiDroidPackageName(context) != null
        }
    }

    /**
     * Try to find the given deck by name, accounting for potential renaming of the deck by the user as follows:
     * If there's a deck with deckName then return it's ID
     * If there's no deck with deckName, but a ref to deckName is stored in SharedPreferences, and that deck exist in
     * AnkiDroid (i.e. it was renamed), then use that deck.Note: this deck will not be found if your app is re-installed
     * If there's no reference to deckName anywhere then return null
     * @param deckName the name of the deck to find
     * @return the did of the deck in Anki
     */
    public fun findDeckIdByName(deckName: String) {
        val key = longPreferencesKey(deckName)
        val deckId: Flow<Long?> = context.ankiData.data.map { ankiData ->
            ankiData[key]
        }
        deckId.
    }

    /**
     * I don't know if this does what I want it to do
     */
    public suspend fun storeDeckReference(deckName: String, deckId: Long) {
        val key = longPreferencesKey(deckName)
        context.ankiData.edit { anki ->
            anki[key] = deckId
        }
    }

    public suspend fun storeModelReference(modelName: String, modelId: Long) {
        val key = longPreferencesKey(modelName)
        context.ankiData.edit { anki ->
            anki[key] = modelId
        }
    }

    /**
     * Get the ID of the deck which matches the name
     * @param deckName Exact name of deck (note: deck names are unique in Anki)
     * @return the ID of the deck that has given name, or null if no deck was found or API error
     */
    public fun getDeckId(deckName: String): Long? {
        val mapList: Map<Long, String>? = api.deckList
        if (mapList != null) {
            for (entry in mapList) {
                if (entry.value.equals(other = deckName, ignoreCase = true)) {
                    return entry.key
                }
            }
        }
        return null
    }

    public fun getApi(): AddContentApi {
        return api
    }
}