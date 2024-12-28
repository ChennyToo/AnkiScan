package com.example.ankiscan.anki

// TODO: make most of these fields customizable by the user
// Some fields to store configuration details for Anki card generation
data object AnkiDroidConfig {
    // Name of deck which will be created in AnkiDroid
    val DECK_NAME: String = "AnkiScan"
    // Name of model which will be created in AnkiDroid
    val MODEL_NAME: String = "AnkiScan"
    // Optional space separated list of tags to add to every note
    val TAGS: Set<String> = setOf("ANKI_SCAN")
    // List of field names that will be used in AnkiDroid model
    // List of card names that will be used in AnkiDroid (one for each direction of learning)
    // CSS to share between all the cards (optional). User will need to install the NotoSans font by themselves
}