package com.example.ankiscan.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
    fun getReadingElementEntryNumber(element: String): Flow<Int>?

    @Query("SELECT entry_id from kanji_element WHERE k_element = :element")
    fun getKanjiElementEntryNumber(element: String): Flow<Int>?

    @Query("SELECT * from reading_element WHERE entry_id = :entryId")
    fun getReadingElements(entryId: Int): Flow<List<ReadingElement>>

    @Query("SELECT * from kanji_element WHERE entry_id = :entryId")
    fun getKanjiElements(entryId: Int): Flow<List<KanjiElement>>

    @Query("SELECT * from sense WHERE entry_id = :entryId")
    fun getSenses(entryId: Int): Flow<List<Sense>>
}