package com.example.translater

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TranslationService {
    @GET("language/translate/v2")
    fun translate(
        @Query("q") text: String,
        @Query("target") targetLanguage: String,
        @Query("key") apiKey: String = "YOUR_GOOGLE_TRANSLATE_API_KEY"
    ): Call<TranslationResponse>
}
