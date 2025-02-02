package com.example.ankiscan.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ankiscan.data.DictRepository
import com.example.ankiscan.data.KanjiElement
import com.example.ankiscan.data.ReadingElement
import com.example.ankiscan.data.Sense

// Minimum possible ent_seq value in jmdict
const val MIN_ID = 1_000_000

/**
 * View model for validating and inserting items into the Room database
 * I DON'T THINK WE NEED THIS FILE BUT ILL LEAVE IT HERE
 */
class DictEntryViewModel(private val dictRepository: DictRepository) : ViewModel() {
    /**
     * Holds current dict entry ui state
     */
    var dictEntryUiState by mutableStateOf(DictEntryUiState())
        private set

    fun updateUiState(dictEntryDetails: DictEntryDetails) {
        dictEntryUiState = DictEntryUiState(
            dictEntryDetails = dictEntryDetails, isEntryValid = validateInput(dictEntryDetails)
        )
    }

    suspend fun saveItem() {}

    private fun validateInput(dictEntryDetails: DictEntryDetails = dictEntryUiState.dictEntryDetails): Boolean {
        return with(dictEntryDetails) {
            id > MIN_ID && readingElements.isNotEmpty() && senses.isNotEmpty()
        }
    }
}

data class DictEntryUiState(
    val dictEntryDetails: DictEntryDetails = DictEntryDetails(),
    val isEntryValid: Boolean = false,
)

data class DictEntryDetails(
    val id: Int = -1,
    val kanjiElements: List<KanjiElement>? = null,
    val readingElements: List<ReadingElement> = listOf(),
    val senses: List<Sense> = listOf(),
)