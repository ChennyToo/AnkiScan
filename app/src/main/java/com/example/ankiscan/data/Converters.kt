package com.example.ankiscan.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class Converters {
    // TODO: is it required to serialze nulls?
    private val gson = GsonBuilder().serializeNulls().create()

    @TypeConverter
    fun fromNullableStringList(list: List<String>?): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toNullableStringList(json: String): List<String>? {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(json, type)
    }
}