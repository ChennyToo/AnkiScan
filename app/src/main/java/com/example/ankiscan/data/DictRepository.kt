package com.example.ankiscan.data

import kotlinx.coroutines.flow.Flow

// Functions here map to the DictDao interface
interface DictRepository {
    fun getEntryNumber(element: String, type: ElementType): Flow<Int>?

    fun getReadingElements(entryId: Int): Flow<List<ReadingElement>>

    fun getKanjiElements(entryId: Int): Flow<List<KanjiElement>>

    fun getSenses(entryId: Int): Flow<List<Sense>>

    fun getAnkiFields(word: String): AnkiFields?
}

enum class ElementType {
    KANJI_ELEMENT, READING_ELEMENT
}