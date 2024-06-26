package com.o5appstudio.composeconcepts.quotesapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.o5appstudio.composeconcepts.quotesapp.DataManager

@Composable
fun MainScreen(){
    if(DataManager.isDataLoaded.value){

        if(DataManager.currentPage.value == Pages.LISTING){
            QuoteListScreen(data = DataManager.data) {
                DataManager.switchPages(it)
            }
        } else{
            DataManager.currentQuote?.let { QuoteDetail(quote = it) }
        }


    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(1f)){
            Text(text = "Loading...")
        }
    }

}

enum class Pages{
    LISTING,
    DETAIL
}