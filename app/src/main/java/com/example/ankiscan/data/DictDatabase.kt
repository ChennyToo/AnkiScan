package com.example.ankiscan.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * Persist Data with Room code lab was used as a reference for this class
 *
 * @constructor Create empty Dictionary database
 */
@Database(entities = [ReadingElement::class, KanjiElement::class, Sense::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DictDatabase : RoomDatabase() {
    abstract fun dictEntryDao(): DictDao

    companion object {
        @Volatile
        private var Instance: DictDatabase? = null

        fun getDatabase(context: Context): DictDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, DictDatabase::class.java, "dict_database")
                    .fallbackToDestructiveMigration()
                    .createFromAsset("dictionary.db")
                    .build().also { Instance = it }
            }
        }
    }
}