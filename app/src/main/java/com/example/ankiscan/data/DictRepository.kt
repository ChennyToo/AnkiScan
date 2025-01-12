package com.example.ankiscan.data

import kotlinx.coroutines.flow.Flow

// Functions here map to the DictDao interface
interface DictRepository {
    suspend fun insertEntry(entry: DictEntry)

    suspend fun updateEntry(entry: DictEntry)

    suspend fun deleteEntry(entry: DictEntry)

    fun getEntry(id: Int): Flow<DictEntry?>
}