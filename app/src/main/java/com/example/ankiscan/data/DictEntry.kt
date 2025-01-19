package com.example.ankiscan.data

import Dialect
import Field
import Gender
import GlossType
import KanjiInfo
import Misc
import PartOfSpeech
import Priorities
import ReadingInfo
import SourceLanguageType
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

// TODO: Write type converter for string list and all other custom data types

// Lists that aren't nullable have to contain at least one element according to the XML DTD
// Comments are original element names in xml file
@Entity(tableName = "dictEntries")
@TypeConverters(Converters::class)
data class DictEntry(
    @PrimaryKey val id: Int, // ent_seq
    @ColumnInfo(name = "k_ele") val kanjiElements: List<KanjiElement>?, // k_ele
    @ColumnInfo(name = "r_ele") val readingElements: List<ReadingElement>, // r_ele
    @ColumnInfo(name = "sense") val senses: List<Sense> // sense
) {
    public constructor() : this(0, null, listOf(), listOf())
}

data class KanjiElement(
    val kanjiElement: String, // keb
    val kanjiInfo: List<KanjiInfo>?, // ke_inf
    val kanjiPriorities: List<Priorities>?, // ke_pri
    val sense: Sense // sense
) {
    public constructor() : this("", null, null, Sense())
}

data class ReadingElement(
    val readingElement: String, // reb
    val isTrueReading: Boolean, // re_nokanji
    val correspondingKanji: List<String>?, // re_restr
    val readingInfo: List<ReadingInfo>?, // re_inf
    val readingPriorities: List<Priorities>? // re_pri
) {
    public constructor() : this("", true, null, null, null)
}

data class Sense(
    val correspondingKanji: List<String>?, // stagk
    val correspondingReadings: List<String>?, // stagr
    val partOfSpeech: List<PartOfSpeech>?, // pos
    val synonymRef: List<String>?, // xref
    val antonymRef: List<String>?, // ant
    val field: List<Field>?, // field
    val misc: List<Misc>?, // misc
    val info: List<String>?, // s_inf
    val sourceLanguages: List<SourceLanguage>?, // lsource
    val dialects: List<Dialect>?, // dial
    val glosses: List<Gloss>?, // gloss
    // examples will be generated from data from tatoeba.org
    val examples: List<String>// example
) {
    public constructor() : this(null, null, null, null, null, null, null, null, null, null, null, listOf())
}

data class SourceLanguage(
    val language: Iso639Language, // xml:lang
    val type: SourceLanguageType, // ls_type
    val wasei: Boolean // ls_wasei
) {
    public constructor() : this(Iso639Language.ENGLISH, SourceLanguageType.FULL, false)
}

// These are basically definitions
data class Gloss(
    val definition: String, // PCData
    val language: Iso639Language?, // xml:lang
    val gender: Gender?, // g_gend
    val type: GlossType? // g_type
) {
    public constructor() : this("", null, null, null)
}
