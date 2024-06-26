package com.o5appstudio.composeconcepts.quotesapp.screens

import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.o5appstudio.composeconcepts.quotesapp.models.Quote


@Composable
fun QuoteList(data : Array<Quote>, onClick : (quote:Quote) -> Unit) {
    LazyColumn (
        flingBehavior = ScrollableDefaults.flingBehavior(),
        content = {
            items(data){
                QuoteListItem(quote = it, onClick)
            }
        })
}



