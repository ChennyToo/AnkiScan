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
    val FIELDS: Array<String> = arrayOf("word", "definition")
    // List of card names that will be used in AnkiDroid
    val CARDS: Array<String> = arrayOf("basic")
    // CSS to share between all the cards (optional)
    val CSS: String = ".card {\n" +
            "    font-family: arial;\n" +
            "    font-size: 20px;\n" +
            "    text-align: center;\n" +
            "    color: black;\n" +
            "    background-color: white;\n" +
            "}"
    // Template for the question of each card
    val QFMT1: String = "{{Word}}"
    val QFMT: Array<String> = arrayOf(QFMT1)
    // Template for the answer
    val AMFT1: String = "{{FrontSide}}\n" +
            "\n" +
            "<hr id=answer>\n" +
            "\n" +
            "{{Definition}}"
    val AMFT: Array<String> = arrayOf(AMFT1)
}