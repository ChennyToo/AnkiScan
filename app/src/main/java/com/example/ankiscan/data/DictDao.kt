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
    suspend fun getReadingElementEntryNumber(element: String): Int?

    @Query("SELECT entry_id from kanji_element WHERE k_element = :element")
    suspend fun getKanjiElementEntryNumber(element: String): Int?

    @Query("SELECT * from reading_element WHERE entry_id = :entryId")
    suspend fun getReadingElements(entryId: Int): List<ReadingElement>

    @Query("SELECT * from kanji_element WHERE entry_id = :entryId")
    suspend fun getKanjiElements(entryId: Int): List<KanjiElement>

    @Query("SELECT * from sense WHERE entry_id = :entryId")
    suspend fun getSenses(entryId: Int): List<Sense>
}