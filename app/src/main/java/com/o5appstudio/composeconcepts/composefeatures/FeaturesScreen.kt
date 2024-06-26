package com.o5appstudio.composeconcepts.composefeatures

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.o5appstudio.composeconcepts.composefeatures.navigation.NavScreens

@Composable
fun Features(navHostController: NavHostController, context: Context){
    val scrollState = rememberScrollState()
    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(color = Color.LightGray)
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    scrollState,
                )

        ){
            SwitchOp()
            LoginScreen()
            HeightUI(dp = 10.dp)
            LoadNetworkImage(imageUrl = "https://4.bp.blogspot.com/-8oh4q_BoZr4/VonezAKIGOI/AAAAAAAAAUw/gAFtiXO3B-w/s1600-r/edited%2Bdp.jpg", imageSize = 200.dp, corners = 14.dp)
            HeightUI(dp = 10.dp)
            ImageRowItem(imageData = ImageList(image = "https://4.bp.blogspot.com/-8oh4q_BoZr4/VonezAKIGOI/AAAAAAAAAUw/gAFtiXO3B-w/s1600-r/edited%2Bdp.jpg", description = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words")){
                Toast.makeText(context, "Single Item Clicked", Toast.LENGTH_SHORT).show()
            }
            RecyclerView(imageList = dummyData()){
                Toast.makeText(context, it.description, Toast.LENGTH_SHORT).show()
            }
            HeightUI(dp = 10.dp)
            ButtonCustom(text = "Go to Screen One") {
                navHostController.navigate("${NavScreens.ScreenOne.screenName}/Helllo jeee")
            }
        }
    }

}