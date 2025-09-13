package com.example.quotesApp.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.quotesApp.model.QuoteModel

@Composable
fun QuoteList(data: Array<QuoteModel>, onClick: (quoteModel: QuoteModel) -> Unit) {
    LazyColumn(content = {
        items(data) { quoteItems ->
            QuotesListItem(quoteItems, onClick)
        }
    })
}