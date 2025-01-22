package com.example.ankiscan.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DictDao {
//    @Insert(onConflict = OnConflictStrategy.ABORT)
//    suspend fun insert(dictEntry: DictEntry)
//
//    @Update
//    suspend fun update(dictEntry: DictEntry)
//
//    @Delete
//    suspend fun delete(dictEntry: DictEntry)
//
//    @Query("SELECT * from dictEntries WHERE id = :id")
//    fun getEntry(id: Int): Flow<DictEntry>
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertReadingElement(readingElement: ReadingElement)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertKanjiElement(kanjiElement: KanjiElement)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertSense(sense: Sense)

}