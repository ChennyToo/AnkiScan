package com.example.ankiscan

import android.app.Application
import com.example.ankiscan.data.DictDatabase
import com.example.ankiscan.data.DictRepository
import com.example.ankiscan.data.OfflineDictRepository

class AnkiScanApplication: Application() {
    private val database: DictDatabase by lazy { DictDatabase.getDatabase(this) }
    val dictRepository: DictRepository by lazy { OfflineDictRepository(database.dictEntryDao()) }

}