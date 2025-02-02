package com.example.ankiscan.data

import dev.esnault.wanakana.core.Wanakana
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking

class OfflineDictRepository(private val dictDao: DictDao) : DictRepository {
    override fun getEntryNumber(element: String, type: ElementType): Flow<Int>? {
        return when (type) {
            ElementType.KANJI_ELEMENT -> dictDao.getKanjiElementEntryNumber(element)
            ElementType.READING_ELEMENT -> dictDao.getReadingElementEntryNumber(element)
        }
    }

    override fun getReadingElements(entryId: Int): Flow<List<ReadingElement>> {
        return dictDao.getReadingElements(entryId)
    }

    override fun getKanjiElements(entryId: Int): Flow<List<KanjiElement>> {
        return dictDao.getKanjiElements(entryId)
    }

    override fun getSenses(entryId: Int): Flow<List<Sense>> {
        return dictDao.getSenses(entryId)
    }

    override fun getAnkiFields(word: String): AnkiFields? {
        var entryId: Int? = null;
        var elementType: ElementType? = null; // This will be helpful when rewriting this function
        // Get the entry number of the word
        if (Wanakana.isKana(word)) {
            entryId = getEntryNumberValue(word, ElementType.READING_ELEMENT)
            elementType = ElementType.READING_ELEMENT
        } else { // Probably kanji
            entryId = getEntryNumberValue(word, ElementType.KANJI_ELEMENT)
            elementType = ElementType.KANJI_ELEMENT
        }

        if (entryId == null) { // We didn't find the entry for the given word
            return null
        }

        // Get the definitions and put them in a nice format
        val glosses: MutableList<String> = mutableListOf()
        getSenseValues(entryId).forEach { sense ->
            if (sense.glosses != null) {
                for (gloss in sense.glosses) {
                    glosses.add(gloss)
                }
            }
        }

        return AnkiFields(word, glosses)
    }

    private fun getEntryNumberValue(element: String, type: ElementType): Int? {
        var entryNumber: Int? = null
        runBlocking {
            getEntryNumber(element, type)?.collect() {
                entryNumber = it
            }
        }
        return entryNumber
    }

    private suspend fun getReadingElementsValues(entryId: Int): List<ReadingElement> {
        return getReadingElements(entryId).last()
    }

    private fun getKanjiElementsValues(entryId: Int): List<KanjiElement> {
        var kanjiElements: List<KanjiElement> = listOf()
        runBlocking {
            getKanjiElements(entryId).collect() {
                kanjiElements = it
            }
        }
        return kanjiElements
    }

    private fun getSenseValues(entryId: Int): List<Sense> {
        var senses: List<Sense> = listOf()
        runBlocking {
            getSenses(entryId).collect() {
                senses = it
            }
        }
        return senses
    }
}