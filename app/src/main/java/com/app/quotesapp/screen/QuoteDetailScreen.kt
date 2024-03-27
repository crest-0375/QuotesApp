package com.app.quotesapp.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.app.quotesapp.QuoteManager
import com.app.quotesapp.R
import com.app.quotesapp.models.Quote


@Composable
fun QuoteDetail(quote: Quote) {

    BackHandler() {
        QuoteManager.switchPages(null)
    }

    Box(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(
                Brush.sweepGradient(
                    colors = listOf(
                        Color.White,
                        Color.LightGray
                    )
                )
            )
    ) {
        Card(
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier
                .padding(32.dp)
                .align(Alignment.Center)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp, 24.dp)
            ) {
                Image(
                    imageVector = Icons.Filled.FormatQuote,
                    contentDescription = "Quote",
                    modifier = Modifier
                        .size(80.dp)
                        .rotate(180f)
                )
                Text(
                    text = quote.quote,
                    fontFamily = FontFamily(Font(R.font.playfair_display)),
                    style = MaterialTheme.typography.labelLarge
                )
                Spacer(modifier = Modifier.padding(16.dp))
                Text(
                    text = quote.author,
                    fontFamily = FontFamily(Font(R.font.playfair_display)),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}