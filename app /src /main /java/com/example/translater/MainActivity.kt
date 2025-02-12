package com.example.translater

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var inputText: EditText
    private lateinit var translateButton: Button
    private lateinit var translatedText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputText = findViewById(R.id.inputText)
        translateButton = findViewById(R.id.translateButton)
        translatedText = findViewById(R.id.translatedText)

        translateButton.setOnClickListener {
            val textToTranslate = inputText.text.toString()
            translateText(textToTranslate)
        }
    }

    private fun translateText(text: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://translation.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(TranslationService::class.java)
        val call = service.translate(text, "en") // Target language: English

        call.enqueue(object : Callback<TranslationResponse> {
            override fun onResponse(call: Call<TranslationResponse>, response: Response<TranslationResponse>) {
                if (response.isSuccessful) {
                    val translation = response.body()?.data?.translations?.firstOrNull()?.translatedText
                    translatedText.text = translation ?: "Translation failed"
                }
            }

            override fun onFailure(call: Call<TranslationResponse>, t: Throwable) {
                translatedText.text = "Error: ${t.message}"
            }
        })
    }
}
