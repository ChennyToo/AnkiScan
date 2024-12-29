package com.example.ankiscan.anki

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ichi2.anki.api.AddContentApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.LinkedList

class AnkiDroidHelper(
    private val context: Context
) {
    private val applicationContext: Context = context.applicationContext
    private val ankiApi: AddContentApi = AddContentApi(applicationContext)

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
    public suspend fun findDeckIdByName(deckName: String) {
        val key = longPreferencesKey(deckName)
        val deckId: Flow<Long?> = context.ankiData.data.map { ankiData ->
            ankiData[key]
        }
        deckId.collect { value ->

        }

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
    fun getDeckId(deckName: String): Long {
        val deckList: Map<Long, String>? = ankiApi.deckList
        if (deckList != null) {
            for (entry in deckList) {
                if (entry.value.equals(other = deckName, ignoreCase = true)) {
                    return entry.key
                }
            }
        }
        Log.e("AnkiDroidHelper", "Failed to fetch model ID")
        return -1
    }

    fun deckExists(deckName: String): Boolean {
        val deckList: Map<Long, String> = ankiApi.deckList
        if (deckList != null) {
            for (entry in deckList) {
                if (entry.value.equals(other = deckName, ignoreCase = true)) {
                    return true
                }
            }
        }
        return false
    }

    fun createDeck(deckName: String): Long {
        return when (deckExists(deckName)) {
            true -> getDeckId(deckName)
            false -> ankiApi.addNewDeck(deckName)
        }
    }

    fun getModelId(modelName: String): Long {
        val modelList: Map<Long, String>? = ankiApi.modelList
        if (modelList != null) {
            for (entry in modelList) {
                if (entry.value.equals(other = modelName, ignoreCase = true)) {
                    return entry.key
                }
            }
        }
        Log.e("AnkiDroidHelper", "Failed to fetch model ID")
        return -1
    }

    fun modelExists(modelName: String): Boolean {
        val modelList: Map<Long, String> = ankiApi.modelList
        if (modelList != null) {
            for (entry in modelList) {
                if (entry.value.equals(other = modelName, ignoreCase = true)) {
                    return true
                }
            }
        }
        return false
    }

    fun createModel(modelName: String): Long {
        return when (modelExists(modelName)) {
            true -> getModelId(modelName)
            false -> ankiApi.addNewCustomModel(
                modelName,
                AnkiDroidConfig.FIELDS,
                AnkiDroidConfig.CARDS,
                AnkiDroidConfig.QFMT,
                AnkiDroidConfig.AMFT,
                AnkiDroidConfig.CSS,
                getDeckId(AnkiDroidConfig.DECK_NAME),
                null
            )
        }
    }

    public fun getApi(): AddContentApi {
        return ankiApi
    }

    public fun addCardToAnkiDroid(word: String, definition: String) {
        val data: Map<String, String> = mapOf(Pair("word", word), Pair("definition", definition))
        val deckName: String = AnkiDroidConfig.DECK_NAME
        val modelName: String = AnkiDroidConfig.MODEL_NAME
        // Get the ID and if it doesn't exist, create the deck
        val deckId: Long = if (deckExists(deckName)) getDeckId(deckName) else createDeck(deckName)

        val modelId: Long =
            if (modelExists(modelName)) getModelId(modelName) else createModel(modelName)
        // If the word already exists, don't add it

        val fieldNames: Array<String> = AnkiDroidConfig.FIELDS
        val fields: MutableList<String> = LinkedList()
        data.forEach { entry ->
            fields.add(entry.value)
        }
        val added: Long? = ankiApi.addNote(
            modelId, deckId, arrayOf(data["word"], data["definition"]), setOf("test")
        )
        if (added == null) {
            Log.e("AnkiDroidHelper", "Failed to add note to AnkiDroid")
        } else {
            Log.d("AnkiDroidHelper", "Added note to AnkiDroid")
        }
    }
}