package com.example.quotesApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.quotesApp.screens.QuoteDetail
import com.example.quotesApp.screens.QuoteListScreen
import com.example.quotesApp.ui.theme.QuotesAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        CoroutineScope(Dispatchers.IO).launch {
            QuoteManager.loadQuotes(applicationContext)
        }
        setContent {
            QuotesAppTheme(content = {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme
                        .colorScheme
                        .background
                ) {
                    App()
                }
            })
        }
    }
}

@Composable
fun App() {
    if (QuoteManager.isDataLoaded.value) {

        if (QuoteManager.currentPage.value == Pages.LISTINGS) {
            QuoteListScreen(data = QuoteManager.data) {
                QuoteManager.switchPages(it)
            }
        } else {
            QuoteManager.currentQuote?.let { QuoteDetail(it) }
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(1f),
            Alignment.Center
        ) {
            Text(
                text = "Loading....",
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}

enum class Pages {
    LISTINGS,
    DETAIL
}

