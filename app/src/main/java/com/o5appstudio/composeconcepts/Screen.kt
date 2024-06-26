package com.o5appstudio.composeconcepts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showSystemUi = true, showBackground = true)

@Composable
fun PreviewItems(){

    LazyColumn(content = {
        items(getCategories()){
            BlogCategory(image = it.image, title = it.title, body = it.body)
        }
    })

}

@Composable
fun BlogCategory(image: Int, title: String, body: String) {

    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier.padding(8.dp)
    ) {

        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ){
            Image(
                painter = painterResource(id = image),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .padding(8.dp)
                    .clip(shape = CircleShape)
                    .border(width = 2.dp, color = Color.LightGray, shape = CircleShape)

            )
            ItemDescription(title, body, Modifier.weight(.8f))
        }

    }
}

@Composable
private fun ItemDescription(title: String, body: String, modifier: Modifier) {
    Column(modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = body,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Thin, fontSize = 12.sp
        )
    }
}

data class Categories(val image:Int, val title:String, val body:String)

fun getCategories(): MutableList<Categories> {

    val list = mutableListOf<Categories>()
    list.add(Categories(R.drawable.ic_launcher_background, "Zain", "Programmer"))
    list.add(Categories(R.drawable.ic_launcher_background, "Ali", "Driver"))
    list.add(Categories(R.drawable.ic_launcher_background, "Adil", "Shop Keeper"))
    list.add(Categories(R.drawable.ic_launcher_background, "Hassan", "Store Manager"))
    list.add(Categories(R.drawable.ic_launcher_background, "Usman", "Senior Developer"))
    list.add(Categories(R.drawable.ic_launcher_background, "Haider", "Business Man"))
    list.add(Categories(R.drawable.ic_launcher_background, "Tawakal", "Property Dealer"))
    list.add(Categories(R.drawable.ic_launcher_background, "Zain", "Programmer"))
    list.add(Categories(R.drawable.ic_launcher_background, "Ali", "Driver"))
    list.add(Categories(R.drawable.ic_launcher_background, "Adil", "Shop Keeper"))
    list.add(Categories(R.drawable.ic_launcher_background, "Hassan", "Store Manager"))
    list.add(Categories(R.drawable.ic_launcher_background, "Usman", "Senior Developer"))
    list.add(Categories(R.drawable.ic_launcher_background, "Haider", "Business Man"))
    list.add(Categories(R.drawable.ic_launcher_background, "Tawakal", "Property Dealer"))
    return list
}