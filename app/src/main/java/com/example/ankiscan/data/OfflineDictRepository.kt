package com.example.ankiscan.data

import kotlinx.coroutines.flow.Flow

class OfflineDictRepository(private val dictDao: DictDao) : DictRepository {
    override fun getEntryNumber(element: String, type: ElementType): Flow<Int> {
        return when (type) {
            ElementType.KANJI_ELEMENT -> dictDao.getKanjiElementEntryNumber(element)
            ElementType.READING_ELEMENT -> dictDao.getReadingElementEntryNumber(element)
        }
    }

    override fun getReadingElement(entryId: Int): Flow<ReadingElement> {
        return dictDao.getReadingElement(entryId)
    }

    override fun getKanjiElement(entryId: Int): Flow<KanjiElement> {
        return dictDao.getKanjiElement(entryId)
    }

    override fun getSense(entryId: Int): Flow<Sense> {
        return dictDao.getSense(entryId)
    }
}