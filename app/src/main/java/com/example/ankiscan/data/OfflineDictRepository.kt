package com.example.ankiscan.data

import kotlinx.coroutines.flow.Flow

class OfflineDictRepository(private val dictDao: DictDao) : DictRepository {
    override suspend fun insertEntry(entry: DictEntry) = dictDao.insert(entry)

    override suspend fun updateEntry(entry: DictEntry) = dictDao.update(entry)

    override suspend fun deleteEntry(entry: DictEntry) = dictDao.delete(entry)

    override fun getEntry(id: Int): Flow<DictEntry?> = dictDao.getEntry(id)
}