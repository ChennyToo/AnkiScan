package com.example.ankiscan.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Persist Data with Room code lab was used as a reference for this class
 *
 * @constructor Create empty Dictionary database
 */
@Database(entities = [DictEntry::class], version = 1, exportSchema = false)
abstract class DictDatabase : RoomDatabase() {
    abstract fun dictEntryDao(): DictDao

    companion object {
        @Volatile
        private var Instance: DictDatabase? = null

        fun getDatabase(context: Context): DictDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, DictDatabase::class.java, "dict_database")
                    .fallbackToDestructiveMigration()
                    //.addCallback() // TODO: create an input stream of entries
                    .build().also { Instance = it }
            }
        }
    }
}