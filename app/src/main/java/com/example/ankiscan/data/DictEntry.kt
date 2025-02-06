package com.example.ankiscan.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reading_element")
data class ReadingElement(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "entry_id")
    val entryId: Int,
    @ColumnInfo(name = "r_element")
    val element: String,
    @ColumnInfo(name = "is_true_reading")
    val isTrueReading: Boolean,
    @ColumnInfo(name = "related_kanji")
    val relatedKanji: List<String>?,
    @ColumnInfo(name = "reading_info")
    val readingInfo: List<String>?,
    @ColumnInfo(name = "reading_priority")
    val readingPriorities: List<String>?,
)

@Entity(tableName = "kanji_element")
data class KanjiElement(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "entry_id")
    val entryId: Int,
    @ColumnInfo(name = "k_element")
    val element: String,
    val info: List<String>?,
    val priority: List<String>?,
)

@Entity(tableName = "sense")
data class Sense(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "entry_id")
    val entryId: Int,
    @ColumnInfo(name = "related_kanji")
    val relatedKanji: List<String>?,
    @ColumnInfo(name = "related_reading")
    val correspondingReadings: List<String>?,
    @ColumnInfo(name = "part_of_speech")
    val partsOfSpeech: List<String>?,
    @ColumnInfo(name = "cross_ref")
    val crossReferences: List<String>?,
    @ColumnInfo(name = "antonym_ref")
    val antonymReferences: List<String>?,
    @ColumnInfo(name = "field_of_application")
    val fieldsOfApplication: List<String>?,
    val misc: List<String>?,
    @ColumnInfo(name = "source_language")
    val sourceLanguages: List<String>?,
    @ColumnInfo(name = "dialect")
    val dialects: List<String>?,
    @ColumnInfo(name = "gloss")
    val glosses: List<String>?,
    @ColumnInfo(name = "sense_info")
    val senseInfo: List<String>?,
    // TODO: add examples from tatoeba
)