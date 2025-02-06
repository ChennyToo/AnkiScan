package com.example.ankiscan.data

import dev.esnault.wanakana.core.Wanakana
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class OfflineDictRepository(
    private val dictDao: DictDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : DictRepository {
    override suspend fun getEntryNumber(element: String, type: ElementType): Int? {
        return withContext(dispatcher) {
            when (type) {
                ElementType.KANJI_ELEMENT -> dictDao.getKanjiElementEntryNumber(element)
                ElementType.READING_ELEMENT -> dictDao.getReadingElementEntryNumber(element)
            }
        }
    }

    override suspend fun getReadingElements(entryId: Int): List<ReadingElement> {
        return withContext(dispatcher) {
            dictDao.getReadingElements(entryId)
        }
    }

    override suspend fun getKanjiElements(entryId: Int): List<KanjiElement> {
        return withContext(dispatcher)  {
            dictDao.getKanjiElements(entryId)
        }
    }

    override suspend fun getSenses(entryId: Int): List<Sense> {
        return withContext(dispatcher) {
            dictDao.getSenses(entryId)
        }
    }

    override suspend fun getAnkiFields(word: String): AnkiFields? {
        // All other methods in the repository are main-safe, so no need
        // to move execution to a thread dispatcher
        var entryNumber: Int? = getEntryNumber(word, ElementType.READING_ELEMENT)
        if (entryNumber == null) {
            entryNumber = getEntryNumber(word, ElementType.KANJI_ELEMENT)
        }

        // Didn't find word
        if (entryNumber == null) {
            return AnkiFields("", listOf())
        }

        val senses = getSenses(entryNumber)

        return AnkiFields(word, senses[0].glosses ?: listOf())
    }
}