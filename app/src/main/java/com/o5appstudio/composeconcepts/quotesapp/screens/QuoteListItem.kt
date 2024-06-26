package com.o5appstudio.composeconcepts.quotesapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.o5appstudio.composeconcepts.quotesapp.models.Quote

@Composable
fun QuoteListItem(quote: Quote, onClick: (quote: Quote) -> Unit) {

    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.padding(8.dp)
            .clickable { onClick(quote) },
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier.size(40.dp)
                    .background(color = Color.Black)
                    .padding(5.dp)
            ) {
                Image(
                    imageVector = Icons.Outlined.Favorite,
                    contentDescription = "Quote",
                    colorFilter = ColorFilter.tint(Color.White),
                    alignment = Alignment.TopStart,
                    modifier = Modifier
                        .size(40.dp)
                        //                    .rotate(180F)

                )
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Column (
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = quote.text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(0.dp,0.dp,0.dp,8.dp)
                )
                Box(modifier = Modifier
                    .background(color = Color.LightGray)
                    .fillMaxWidth(.4f)
                    .height(1.dp)
                )
                quote.author?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

            }

        }

    }

}

