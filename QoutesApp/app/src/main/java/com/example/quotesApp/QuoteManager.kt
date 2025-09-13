package com.example.quotesApp

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.example.quotesApp.model.QuoteModel
import com.google.gson.Gson

object QuoteManager {
    var data = emptyArray<QuoteModel>()
    var currentPage = mutableStateOf(Pages.LISTINGS)
    var isDataLoaded = mutableStateOf(false)
    var currentQuote: QuoteModel? = null


    fun loadQuotes(context: Context) {
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        data = gson.fromJson(json, Array<QuoteModel>::class.java)
        isDataLoaded.value = true
    }

    fun switchPages(quoteModel: QuoteModel?) {
        if (currentPage.value == Pages.LISTINGS) {
            currentQuote = quoteModel
            currentPage.value = Pages.DETAIL
        } else {
            currentPage.value = Pages.LISTINGS
        }
    }
}