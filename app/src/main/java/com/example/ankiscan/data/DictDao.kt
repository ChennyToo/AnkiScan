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
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertReadingElement(readingElement: ReadingElement)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertKanjiElement(kanjiElement: KanjiElement)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertSense(sense: Sense)

    @Query("SELECT entry_id from reading_element WHERE r_element = :element")
    fun getReadingElementEntryNumber(element: String): Flow<Int>

    @Query("SELECT entry_id from kanji_element WHERE k_element = :element")
    fun getKanjiElementEntryNumber(element: String): Flow<Int>

    @Query("SELECT * from reading_element WHERE entry_id = :entryId")
    fun getReadingElement(entryId: Int): Flow<ReadingElement>

    @Query("SELECT * from kanji_element WHERE entry_id = :entryId")
    fun getKanjiElement(entryId: Int): Flow<KanjiElement>

    @Query("SELECT * from gloss WHERE entry_id = :entryId")
    fun getSense(entryId: Int): Flow<Sense>
}