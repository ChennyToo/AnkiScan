package com.example.ankiscan.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class Converters {
    // TODO: is it required to serialze nulls?
    private val gson = GsonBuilder().serializeNulls().create()
    @TypeConverter
    fun fromKanjiElements(kanjiElements: List<KanjiElement>): String {
        return gson.toJson(kanjiElements)
    }

    @TypeConverter
    fun toKanjiElements(json: String): List<KanjiElement> {
        // This line is funky, but Kotlin doesn't save generics at runtime, so this allows Gson to
        // know that we are giving it a list of KanjiElements, not a list of some other type.
        val type = object : TypeToken<List<KanjiElement>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromReadingElements(readingElements: List<ReadingElement>): String {
        return gson.toJson(readingElements)
    }

    @TypeConverter
    fun toReadingElements(json: String): List<ReadingElement> {
        val type = object : TypeToken<List<ReadingElement>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromSenses(readingElements: List<Sense>): String {
        return gson.toJson(readingElements)
    }

    @TypeConverter
    fun toSenses(json: String): List<Sense> {
        val type = object : TypeToken<List<Sense>>() {}.type
        return gson.fromJson(json, type)
    }
}