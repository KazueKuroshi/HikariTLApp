package com.example.translater

data class TranslationResponse(
    val data: TranslationData
)

data class TranslationData(
    val translations: List<Translation>
)

data class Translation(
    val translatedText: String
)
