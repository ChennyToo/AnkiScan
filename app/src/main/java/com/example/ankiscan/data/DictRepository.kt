package com.example.ankiscan.data

import kotlinx.coroutines.flow.Flow

// Functions here map to the DictDao interface
interface DictRepository {
    suspend fun getEntryNumber(element: String, type: ElementType): Int?

    suspend fun getReadingElements(entryId: Int): List<ReadingElement>

    suspend fun getKanjiElements(entryId: Int): List<KanjiElement>

    suspend fun getSenses(entryId: Int): List<Sense>

    suspend fun getAnkiFields(word: String): AnkiFields?
}

enum class ElementType {
    KANJI_ELEMENT, READING_ELEMENT
}