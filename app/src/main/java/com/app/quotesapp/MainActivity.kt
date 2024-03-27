package com.app.quotesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.app.quotesapp.screen.QuoteDetail
import com.app.quotesapp.screen.QuoteListScreen
import com.app.quotesapp.ui.theme.QuotesAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            QuoteManager.loadAssetsFromFile(applicationContext)
        }
        setContent {
            QuotesAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    if (QuoteManager.isDataLoaded.value) {
        if (QuoteManager.currentPage.value == Pages.LISTING)
            QuoteListScreen(data = QuoteManager.data) {
                QuoteManager.switchPages(it)
            }
        else {
            QuoteManager.currentQuote?.let { QuoteDetail(quote = it) }
        }
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(1f)
        ) {
            Text(text = "Loading...", style = MaterialTheme.typography.labelLarge)
        }
    }
}

enum class Pages {
    LISTING,
    DETAIL,
}