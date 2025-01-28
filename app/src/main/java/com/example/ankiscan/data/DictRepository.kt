package com.example.ankiscan.data

import kotlinx.coroutines.flow.Flow

// Functions here map to the DictDao interface
interface DictRepository {
    fun getEntryNumber(element: String, type: ElementType): Flow<Int>

    fun getReadingElement(entryId: Int): Flow<ReadingElement>

    fun getKanjiElement(entryId: Int): Flow<KanjiElement>

    fun getSense(entryId: Int): Flow<Sense>
}

enum class ElementType {
    KANJI_ELEMENT, READING_ELEMENT
}